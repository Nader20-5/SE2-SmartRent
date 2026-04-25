package com.smartrent.property.service.impl;

import com.smartrent.property.dto.FavoriteResponseDto;
import com.smartrent.property.mapper.PropertyMapper;
import com.smartrent.property.repository.FavoriteRepository;
import com.smartrent.property.repository.PropertyRepository;
import com.smartrent.property.service.interfaces.IFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoriteServiceImpl implements IFavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final PropertyRepository propertyRepository;
    private final PropertyMapper propertyMapper;

    @Override
    public List<FavoriteResponseDto> getTenantFavorites(Long tenantId) {
        // TODO: implement
        return null;
    }

    @Override
    public void addFavorite(Long tenantId, Long propertyId) {
        // TODO: implement
    }

    @Override
    public void removeFavorite(Long tenantId, Long propertyId) {
        // TODO: implement
    }
}
