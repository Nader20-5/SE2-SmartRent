package com.smartrent.user.controller;

import com.smartrent.user.dto.UpdateProfileDto;
import com.smartrent.user.dto.UserResponseDto;
import com.smartrent.user.service.interfaces.IAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IAuthService authService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getOwnProfile(@RequestHeader("X-User-Id") Long userId) {
        // TODO: implement
        return null;
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateOwnProfile(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody UpdateProfileDto dto) {
        // TODO: implement
        return null;
    }

    @GetMapping("/{id}/internal")
    public ResponseEntity<UserResponseDto> getUserInternal(@PathVariable Long id) {
        // TODO: implement — service-to-service only
        return null;
    }
}
