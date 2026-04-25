package com.smartrent.property.mapper;

import com.smartrent.property.dto.CreatePropertyDto;
import com.smartrent.property.dto.PropertyResponseDto;
import com.smartrent.property.dto.PropertySummaryDto;
import com.smartrent.property.model.Property;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PropertyMapper {

    @Mapping(target = "imageUrls", ignore = true)
    @Mapping(target = "amenities", ignore = true)
    @Mapping(target = "averageRating", ignore = true)
    @Mapping(target = "reviewCount", ignore = true)
    PropertyResponseDto toResponseDto(Property property);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "landlordId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "isAvailable", ignore = true)
    @Mapping(target = "mainImageUrl", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "images", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    Property toEntity(CreatePropertyDto dto);

    PropertySummaryDto toSummaryDto(Property property);
}
