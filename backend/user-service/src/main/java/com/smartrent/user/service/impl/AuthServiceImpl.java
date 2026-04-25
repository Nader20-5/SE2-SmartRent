package com.smartrent.user.service.impl;

import com.smartrent.user.dto.*;
import com.smartrent.user.mapper.UserMapper;
import com.smartrent.user.repository.UserRepository;
import com.smartrent.user.security.JwtUtil;
import com.smartrent.user.service.interfaces.IAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public AuthResponseDto register(RegisterDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public AuthResponseDto login(LoginDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public UserResponseDto getOwnProfile(Long userId) {
        // TODO: implement
        return null;
    }

    @Override
    public UserResponseDto updateOwnProfile(Long userId, UpdateProfileDto dto) {
        // TODO: implement
        return null;
    }
}
