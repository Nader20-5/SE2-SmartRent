package com.smartrent.user.controller;

import com.smartrent.user.dto.UpdateProfileDto;
import com.smartrent.user.dto.UserResponseDto;
import com.smartrent.user.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final AuthServiceImpl authService;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getOwnProfile(@RequestHeader("X-User-Id") Long userId) {
        return ResponseEntity.ok(authService.getOwnProfile(userId));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponseDto> updateOwnProfile(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody UpdateProfileDto dto) {
        return ResponseEntity.ok(authService.updateOwnProfile(userId, dto));
    }

    /**
     * Internal endpoint — called by Feign from other microservices.
     * No JWT required; this is service-to-service communication.
     */
    @GetMapping("/{id}/internal")
    public ResponseEntity<UserResponseDto> getUserInternal(@PathVariable Long id) {
        return ResponseEntity.ok(authService.getUserInternal(id));
    }
}
