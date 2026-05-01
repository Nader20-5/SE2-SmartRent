package com.smartrent.property.controller;

import com.smartrent.property.dto.*;
import com.smartrent.property.service.interfaces.IPropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final IPropertyService propertyService;

    // ── Search (public — open to all) ────────────────────────────
    @GetMapping("/search")
    public ResponseEntity<Page<PropertyResponseDto>> searchProperties(
            PropertySearchParams params) {
        return ResponseEntity.ok(propertyService.searchProperties(params));
    }

    // ── Get by ID (public) ───────────────────────────────────────
    @GetMapping("/{propertyId}")
    public ResponseEntity<PropertyResponseDto> getProperty(
            @PathVariable Long propertyId) {
        return ResponseEntity.ok(propertyService.getPropertyById(propertyId));
    }

    // ── Create (landlord) ────────────────────────────────────────
    @PostMapping
    public ResponseEntity<PropertyResponseDto> createProperty(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreatePropertyDto dto) {
        PropertyResponseDto created = propertyService.createProperty(userId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // ── Update (landlord — ownership checked in service) ─────────
    @PutMapping("/{propertyId}")
    public ResponseEntity<PropertyResponseDto> updateProperty(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId,
            @Valid @RequestBody UpdatePropertyDto dto) {
        return ResponseEntity.ok(propertyService.updateProperty(userId, propertyId, dto));
    }

    // ── Delete (landlord — cascades images & amenities) ──────────
    @DeleteMapping("/{propertyId}")
    public ResponseEntity<Void> deleteProperty(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId) {
        propertyService.deleteProperty(userId, propertyId);
        return ResponseEntity.noContent().build();
    }

    // ── Upload images (landlord — multipart/form-data) ───────────
    @PostMapping(value = "/{propertyId}/images", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PropertyResponseDto> uploadImages(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId,
            @RequestParam("files") List<MultipartFile> files) {
        PropertyResponseDto updated = propertyService.uploadImages(userId, propertyId, files);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    // ── Delete image (landlord) ──────────────────────────────────
    @DeleteMapping("/{propertyId}/images/{imageId}")
    public ResponseEntity<Void> deleteImage(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId,
            @PathVariable Long imageId) {
        propertyService.deleteImage(userId, propertyId, imageId);
        return ResponseEntity.noContent().build();
    }

    // ── Update status (admin only — checks X-User-Role) ──────────
    @PatchMapping("/{propertyId}/status")
    public ResponseEntity<PropertyResponseDto> updatePropertyStatus(
            @RequestHeader("X-User-Role") String userRole,
            @PathVariable Long propertyId,
            @Valid @RequestBody UpdateStatusDto dto) throws AccessDeniedException {

        if (!"ADMIN".equalsIgnoreCase(userRole)) {
            throw new AccessDeniedException("Only ADMIN users can update property status");
        }

        return ResponseEntity.ok(
                propertyService.updatePropertyStatus(propertyId, dto.getStatus(), dto.getRejectionReason()));
    }

    // ── Landlord's own listings ──────────────────────────────────
    @GetMapping("/landlord")
    public ResponseEntity<Page<PropertyResponseDto>> getLandlordProperties(
            @RequestHeader("X-User-Id") Long userId,
            Pageable pageable) {
        return ResponseEntity.ok(propertyService.getLandlordProperties(userId, pageable));
    }

    // ── Internal endpoint (consumed by visit-service / rental-service) ─
    @GetMapping("/{propertyId}/internal")
    public ResponseEntity<PropertySummaryDto> getPropertyInternal(
            @PathVariable Long propertyId) {
        return ResponseEntity.ok(propertyService.getPropertyInternal(propertyId));
    }
}
