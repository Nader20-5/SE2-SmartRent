package com.smartrent.rental.controller;

import com.smartrent.rental.dto.*;
import com.smartrent.rental.model.DocumentType;
import com.smartrent.rental.service.interfaces.IRentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final IRentalService rentalService;

    @PostMapping
    public ResponseEntity<RentalResponseDto> createApplication(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreateRentalDto dto) {
        // TODO: implement
        return null;
    }

    @PostMapping("/{id}/documents")
    public ResponseEntity<RentalResponseDto> uploadDocuments(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @RequestParam DocumentType type,
            @RequestParam("file") MultipartFile file) {
        // TODO: implement
        return null;
    }

    @GetMapping("/my")
    public ResponseEntity<List<RentalResponseDto>> getTenantApplications(
            @RequestHeader("X-User-Id") Long userId) {
        // TODO: implement
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalResponseDto> getApplicationById(
            @RequestHeader("X-User-Id") Long userId,
            @RequestHeader("X-User-Role") String role,
            @PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> withdrawApplication(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @GetMapping("/landlord")
    public ResponseEntity<List<RentalResponseDto>> getLandlordApplications(
            @RequestHeader("X-User-Id") Long userId) {
        // TODO: implement
        return null;
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<RentalResponseDto> approveApplication(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<RentalResponseDto> rejectApplication(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody RejectRentalDto dto) {
        // TODO: implement
        return null;
    }
}
