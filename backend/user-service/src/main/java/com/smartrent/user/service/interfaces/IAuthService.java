package com.smartrent.user.service.interfaces;

import com.smartrent.user.dto.*;

public interface IAuthService {
    AuthResponseDto register(RegisterDto dto);
    AuthResponseDto login(LoginDto dto);
    UserResponseDto getOwnProfile(Long userId);
    UserResponseDto updateOwnProfile(Long userId, UpdateProfileDto dto);
}
