package com.smartrent.property.service.impl;

import com.smartrent.property.client.UserServiceClient;
import com.smartrent.property.dto.*;
import com.smartrent.property.mapper.PropertyMapper;
import com.smartrent.property.model.PropertyStatus;
import com.smartrent.property.repository.PropertyAmenityRepository;
import com.smartrent.property.repository.PropertyImageRepository;
import com.smartrent.property.repository.PropertyRepository;
import com.smartrent.property.service.interfaces.IPropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PropertyServiceImpl implements IPropertyService {

    private final PropertyRepository propertyRepository;
    private final PropertyImageRepository propertyImageRepository;
    private final PropertyAmenityRepository propertyAmenityRepository;
    private final PropertyMapper propertyMapper;
    private final UserServiceClient userServiceClient;

    @Override
    public PropertyResponseDto createProperty(Long landlordId, CreatePropertyDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public PropertyResponseDto getPropertyById(Long id) {
        // TODO: implement
        return null;
    }

    @Override
    public Page<PropertyResponseDto> searchProperties(PropertySearchParams params) {
        // TODO: implement
        return null;
    }

    @Override
    public Page<PropertyResponseDto> getLandlordProperties(Long landlordId, Pageable pageable) {
        // TODO: implement
        return null;
    }

    @Override
    public PropertyResponseDto updateProperty(Long landlordId, Long propertyId, UpdatePropertyDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public void deleteProperty(Long landlordId, Long propertyId) {
        // TODO: implement
    }

    @Override
    public void uploadImages(Long landlordId, Long propertyId, List<MultipartFile> files) {
        // TODO: implement
    }

    @Override
    public void deleteImage(Long landlordId, Long propertyId, Long imageId) {
        // TODO: implement
    }

    @Override
    public PropertyResponseDto updatePropertyStatus(Long propertyId, PropertyStatus status) {
        // TODO: implement
        return null;
    }

    @Override
    public PropertySummaryDto getPropertyInternal(Long id) {
        // TODO: implement
        return null;
    }
}
