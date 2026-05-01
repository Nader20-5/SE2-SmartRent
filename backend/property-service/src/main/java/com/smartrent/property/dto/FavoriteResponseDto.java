package com.smartrent.property.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResponseDto {
    private Long id;
    private Long propertyId;
    private String propertyTitle;
    private String mainImageUrl;
    private BigDecimal monthlyRent;
    private String city;
}
