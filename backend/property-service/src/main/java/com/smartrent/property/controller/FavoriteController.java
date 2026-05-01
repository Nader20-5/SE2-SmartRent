package com.smartrent.property.controller;

import com.smartrent.property.dto.FavoriteResponseDto;
import com.smartrent.property.service.interfaces.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorites")
@RequiredArgsConstructor
public class FavoriteController {

    private final IFavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<FavoriteResponseDto>> getTenantFavorites(
            @RequestHeader("X-User-Id") Long userId) {
        // TODO: implement
        return null;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<Void> addFavorite(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId) {
        // TODO: implement
        return null;
    }

    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> removeFavorite(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId) {
        // TODO: implement
        return null;
    }
}
