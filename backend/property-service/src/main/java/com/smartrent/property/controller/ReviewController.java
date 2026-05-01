package com.smartrent.property.controller;

import com.smartrent.property.dto.CreateReviewDto;
import com.smartrent.property.dto.ReviewResponseDto;
import com.smartrent.property.service.interfaces.IReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final IReviewService reviewService;

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<List<ReviewResponseDto>> getReviewsByProperty(@PathVariable Long propertyId) {
        // TODO: implement
        return null;
    }

    @PostMapping("/property/{propertyId}")
    public ResponseEntity<ReviewResponseDto> createReview(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long propertyId,
            @Valid @RequestBody CreateReviewDto dto) {
        // TODO: implement
        return null;
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewResponseDto> updateReview(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long reviewId,
            @Valid @RequestBody CreateReviewDto dto) {
        // TODO: implement
        return null;
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long reviewId) {
        // TODO: implement
        return null;
    }
}
