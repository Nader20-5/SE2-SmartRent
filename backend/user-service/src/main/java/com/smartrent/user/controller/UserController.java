package com.smartrent.user.controller;

import com.smartrent.user.annotation.Auditable;
import com.smartrent.user.dto.UpdateProfileDto;
import com.smartrent.user.dto.UserResponseDto;
import com.smartrent.user.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getOwnProfile(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(userService.getOwnProfile(userId));
    }

    @Auditable(action = "UPDATE_PROFILE", resourceType = "USER")
    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateOwnProfile(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody UpdateProfileDto dto) {
        return ResponseEntity.ok(userService.updateOwnProfile(userId, dto));
    }

    @GetMapping("/{id}/internal")
    public ResponseEntity<UserResponseDto> getUserInternal(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserInternal(id));
    }
}
