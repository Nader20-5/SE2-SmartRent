package com.smartrent.user.service.impl;

import com.smartrent.user.dto.AdminStatsDto;
import com.smartrent.user.dto.UserResponseDto;
import com.smartrent.user.dto.UserStatusUpdateDto;
import com.smartrent.user.mapper.UserMapper;
import com.smartrent.user.model.Role;
import com.smartrent.user.model.UserStatus;
import com.smartrent.user.repository.UserRepository;
import com.smartrent.user.service.interfaces.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements IAdminService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Page<UserResponseDto> getAllUsers(Role role, UserStatus status, Pageable pageable) {
        // TODO: implement
        return null;
    }

    @Override
    public UserResponseDto updateUserStatus(Long userId, UserStatusUpdateDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public AdminStatsDto getPlatformStats() {
        // TODO: implement
        return null;
    }
}
