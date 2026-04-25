package com.smartrent.visit.controller;

import com.smartrent.visit.dto.*;
import com.smartrent.visit.service.interfaces.IVisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final IVisitService visitService;

    @PostMapping
    public ResponseEntity<VisitResponseDto> createVisit(
            @RequestHeader("X-User-Id") Long userId,
            @Valid @RequestBody CreateVisitDto dto) {
        // TODO: implement
        return null;
    }

    @GetMapping("/my")
    public ResponseEntity<List<VisitResponseDto>> getTenantVisits(
            @RequestHeader("X-User-Id") Long userId) {
        // TODO: implement
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelVisit(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @GetMapping("/landlord")
    public ResponseEntity<List<VisitResponseDto>> getLandlordVisits(
            @RequestHeader("X-User-Id") Long userId) {
        // TODO: implement
        return null;
    }

    @PatchMapping("/{id}/approve")
    public ResponseEntity<VisitResponseDto> approveVisit(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id) {
        // TODO: implement
        return null;
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<VisitResponseDto> rejectVisit(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody RejectVisitDto dto) {
        // TODO: implement
        return null;
    }

    @PatchMapping("/{id}/reschedule")
    public ResponseEntity<VisitResponseDto> rescheduleVisit(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long id,
            @Valid @RequestBody RescheduleDto dto) {
        // TODO: implement
        return null;
    }
}
