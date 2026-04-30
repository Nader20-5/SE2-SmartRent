package com.smartrent.user.service.impl;

import com.smartrent.user.dto.*;
import com.smartrent.user.exception.AccountNotApprovedException;
import com.smartrent.user.exception.InvalidCredentialsException;
import com.smartrent.user.exception.UserAlreadyExistsException;
import com.smartrent.user.exception.UserNotFoundException;
import com.smartrent.user.mapper.UserMapper;
import com.smartrent.user.model.Role;
import com.smartrent.user.model.User;
import com.smartrent.user.model.UserStatus;
import com.smartrent.user.repository.UserRepository;
import com.smartrent.user.security.JwtUtil;
import com.smartrent.user.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public AuthResponseDto register(RegisterDto dto) {
        // Email check
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("Email already registered: " + dto.getEmail());
        }

        // Build entity from DTO
        User user = userMapper.toEntity(dto);

        // Hash password
        user.setPasswordHash(passwordEncoder.encode(dto.getPassword()));

        // Set status: LANDLORD → PENDING, everyone else → ACTIVE
        user.setStatus(dto.getRole() == Role.LANDLORD ? UserStatus.PENDING : UserStatus.ACTIVE);

        // Save
        user = userRepository.save(user);

        // Generate token
        String token = jwtUtil.generateToken(user);

        // Return AuthResponseDto
        return AuthResponseDto.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @Override
    public AuthResponseDto login(LoginDto dto) {
        // Find user by email
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        // Verify password
        if (!passwordEncoder.matches(dto.getPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        // Status guard: must be ACTIVE
        if (user.getStatus() != UserStatus.ACTIVE) {
            throw new AccountNotApprovedException("Account is not approved. Current status: " + user.getStatus());
        }

        // Generate token
        String token = jwtUtil.generateToken(user);

        // Return AuthResponseDto
        return AuthResponseDto.builder()
                .token(token)
                .userId(user.getId())
                .email(user.getEmail())
                .role(user.getRole().name())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    @Override
    public UserResponseDto getOwnProfile(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return userMapper.toResponseDto(user);
    }

    @Override
    @Transactional
    public UserResponseDto updateOwnProfile(Long userId, UpdateProfileDto dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

        // Patch fields — skip nulls
        if (dto.getFirstName() != null) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getPhone() != null) {
            user.setPhone(dto.getPhone());
        }
        if (dto.getProfilePictureUrl() != null) {
            user.setProfilePictureUrl(dto.getProfilePictureUrl());
        }

        user = userRepository.save(user);
        return userMapper.toResponseDto(user);
    }

    /**
     * Internal endpoint called by Feign from other services.
     * Must never throw 500 — other services depend on this.
     */
    public UserResponseDto getUserInternal(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        return userMapper.toResponseDto(user);
    }
}
