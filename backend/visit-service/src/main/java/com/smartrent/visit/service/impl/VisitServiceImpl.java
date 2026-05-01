package com.smartrent.visit.service.impl;

import com.smartrent.visit.client.PropertyServiceClient;
import com.smartrent.visit.client.UserServiceClient;
import com.smartrent.visit.dto.*;
import com.smartrent.visit.mapper.VisitMapper;
import com.smartrent.visit.repository.VisitRequestRepository;
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
        // TODO: implement
        return null;
    }

    @Override
    public List<VisitResponseDto> getTenantVisits(Long tenantId) {
        // TODO: implement
        return null;
    }

    @Override
    public void cancelVisit(Long tenantId, Long visitId) {
        // TODO: implement
    }

    @Override
    public List<VisitResponseDto> getLandlordVisits(Long landlordId) {
        // TODO: implement
        return null;
    }

    @Override
    public VisitResponseDto approveVisit(Long landlordId, Long visitId) {
        // TODO: implement
        return null;
    }

    @Override
    public VisitResponseDto rejectVisit(Long landlordId, Long visitId, RejectVisitDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public VisitResponseDto rescheduleVisit(Long landlordId, Long visitId, RescheduleDto dto) {
        // TODO: implement
        return null;
    }
}
