package com.smartrent.property.controller;

import com.smartrent.property.dto.*;
import com.smartrent.property.model.PropertyStatus;
import com.smartrent.property.service.interfaces.IPropertyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final IPropertyService propertyService;

    @GetMapping
    public ResponseEntity<Page<PropertyResponseDto>> searchProperties(PropertySearchParams params) {
        // TODO: implement
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponseDto> getProperty(@PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @PostMapping
    public ResponseEntity<PropertyResponseDto> createProperty(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreatePropertyDto dto) {
        // TODO: implement
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponseDto> updateProperty(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody UpdatePropertyDto dto) {
        // TODO: implement
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @PostMapping("/{id}/images")
    public ResponseEntity<Void> uploadImages(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @RequestParam("files") List<MultipartFile> files) {
        // TODO: implement
        return null;
    }

    @DeleteMapping("/{id}/images/{imageId}")
    public ResponseEntity<Void> deleteImage(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @PathVariable Long imageId) {
        // TODO: implement
        return null;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<PropertyResponseDto> updatePropertyStatus(
            @PathVariable Long id,
            @RequestParam PropertyStatus status) {
        // TODO: implement
        return null;
    }

    @GetMapping("/landlord/me")
    public ResponseEntity<Page<PropertyResponseDto>> getLandlordProperties(
            @RequestHeader("X-User-Id") Long userId,
            Pageable pageable) {
        // TODO: implement
        return null;
    }

    @GetMapping("/{id}/internal")
    public ResponseEntity<PropertySummaryDto> getPropertyInternal(@PathVariable Long id) {
        // TODO: implement
        return null;
    }
}
