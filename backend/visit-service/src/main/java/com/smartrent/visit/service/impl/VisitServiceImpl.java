package com.smartrent.visit.service.impl;

import com.smartrent.visit.client.PropertyServiceClient;
import com.smartrent.visit.client.UserServiceClient;
import com.smartrent.visit.dto.*;
import com.smartrent.visit.mapper.VisitMapper;
import com.smartrent.visit.repository.VisitRequestRepository;
import com.smartrent.visit.model.VisitRequest;
import com.smartrent.visit.service.interfaces.IVisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements IVisitService {

    private final VisitRequestRepository visitRequestRepository;
    private final VisitMapper visitMapper;
    private final UserServiceClient userServiceClient;
    private final PropertyServiceClient propertyServiceClient;

    @Override
    public VisitResponseDto createVisit(Long tenantId, CreateVisitDto dto) {
        var property = propertyServiceClient.getPropertyById(dto.getPropertyId());
        
        VisitRequest visitRequest = visitMapper.toEntity(dto);
        visitRequest.setTenantId(tenantId);
        visitRequest.setLandlordId(property.getLandlordId());
        visitRequest.setPropertyTitle(property.getTitle());
        visitRequest.setStatus(com.smartrent.visit.model.VisitStatus.PENDING);
        
        visitRequest = visitRequestRepository.save(visitRequest);
        return visitMapper.toResponseDto(visitRequest);
    }

    @Override
    public List<VisitResponseDto> getTenantVisits(Long tenantId) {
        return visitRequestRepository.findByTenantId(tenantId).stream()
                .map(visitMapper::toResponseDto)
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public void cancelVisit(Long tenantId, Long visitId) {
        VisitRequest visitRequest = visitRequestRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        if (!visitRequest.getTenantId().equals(tenantId)) {
            throw new RuntimeException("Unauthorized");
        }
        visitRequest.setStatus(com.smartrent.visit.model.VisitStatus.CANCELLED);
        visitRequestRepository.save(visitRequest);
    }

    @Override
    public List<VisitResponseDto> getLandlordVisits(Long landlordId) {
        return visitRequestRepository.findByLandlordId(landlordId).stream()
                .map(visit -> {
                    VisitResponseDto dto = visitMapper.toResponseDto(visit);
                    try {
                        var user = userServiceClient.getUserById(visit.getTenantId());
                        dto.setTenantName(user.getFirstName() + " " + user.getLastName());
                        dto.setTenantEmail(user.getEmail());
                    } catch (Exception e) {
                        dto.setTenantName("Unknown Tenant");
                        dto.setTenantEmail("Unknown Email");
                    }
                    return dto;
                })
                .collect(java.util.stream.Collectors.toList());
    }

    @Override
    public VisitResponseDto approveVisit(Long landlordId, Long visitId) {
        VisitRequest visitRequest = visitRequestRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        if (!visitRequest.getLandlordId().equals(landlordId)) {
            throw new RuntimeException("Unauthorized");
        }
        visitRequest.setStatus(com.smartrent.visit.model.VisitStatus.APPROVED);
        visitRequest = visitRequestRepository.save(visitRequest);
        return visitMapper.toResponseDto(visitRequest);
    }

    @Override
    public VisitResponseDto rejectVisit(Long landlordId, Long visitId, RejectVisitDto dto) {
        VisitRequest visitRequest = visitRequestRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        if (!visitRequest.getLandlordId().equals(landlordId)) {
            throw new RuntimeException("Unauthorized");
        }
        visitRequest.setStatus(com.smartrent.visit.model.VisitStatus.REJECTED);
        visitRequest.setRejectionReason(dto.getReason());
        visitRequest = visitRequestRepository.save(visitRequest);
        return visitMapper.toResponseDto(visitRequest);
    }

    @Override
    public VisitResponseDto rescheduleVisit(Long landlordId, Long visitId, RescheduleDto dto) {
        VisitRequest visitRequest = visitRequestRepository.findById(visitId)
                .orElseThrow(() -> new RuntimeException("Visit not found"));
        if (!visitRequest.getLandlordId().equals(landlordId)) {
            throw new RuntimeException("Unauthorized");
        }
        visitRequest.setSuggestedDate(dto.getSuggestedDate());
        visitRequest.setSuggestedTime(dto.getSuggestedTime());
        visitRequest.setNotes(dto.getNotes());
        visitRequest = visitRequestRepository.save(visitRequest);
        return visitMapper.toResponseDto(visitRequest);
    }
}
