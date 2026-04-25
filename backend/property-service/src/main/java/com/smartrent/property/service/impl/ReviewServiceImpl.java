package com.smartrent.property.service.impl;

import com.smartrent.property.dto.CreateReviewDto;
import com.smartrent.property.dto.ReviewResponseDto;
import com.smartrent.property.mapper.ReviewMapper;
import com.smartrent.property.repository.PropertyRepository;
import com.smartrent.property.repository.ReviewRepository;
import com.smartrent.property.service.interfaces.IReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {

    private final ReviewRepository reviewRepository;
    private final PropertyRepository propertyRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewResponseDto> getReviewsByProperty(Long propertyId) {
        // TODO: implement
        return null;
    }

    @Override
    public ReviewResponseDto createReview(Long tenantId, Long propertyId, CreateReviewDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public ReviewResponseDto updateReview(Long tenantId, Long reviewId, CreateReviewDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public void deleteReview(Long tenantId, Long reviewId) {
        // TODO: implement
    }
}
