package com.smartrent.rental.service.impl;

import com.smartrent.rental.client.PropertyServiceClient;
import com.smartrent.rental.client.UserServiceClient;
import com.smartrent.rental.dto.*;
import com.smartrent.rental.mapper.RentalMapper;
import com.smartrent.rental.model.DocumentType;
import com.smartrent.rental.repository.ApplicationDocumentRepository;
import com.smartrent.rental.repository.RentalApplicationRepository;
import com.smartrent.rental.service.interfaces.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements IRentalService {

    private final RentalApplicationRepository rentalApplicationRepository;
    private final ApplicationDocumentRepository applicationDocumentRepository;
    private final RentalMapper rentalMapper;
    private final UserServiceClient userServiceClient;
    private final PropertyServiceClient propertyServiceClient;

    @Override
    public RentalResponseDto createApplication(Long tenantId, CreateRentalDto dto) {
        // TODO: implement
        return null;
    }

    @Override
    public RentalResponseDto uploadDocuments(Long tenantId, Long applicationId, DocumentType type, MultipartFile file) {
        // TODO: implement
        return null;
    }

    @Override
    public List<RentalResponseDto> getTenantApplications(Long tenantId) {
        // TODO: implement
        return null;
    }

    @Override
    public RentalResponseDto getApplicationById(Long requesterId, String requesterRole, Long applicationId) {
        // TODO: implement
        return null;
    }

    @Override
    public void withdrawApplication(Long tenantId, Long applicationId) {
        // TODO: implement
    }

    @Override
    public List<RentalResponseDto> getLandlordApplications(Long landlordId) {
        // TODO: implement
        return null;
    }

    @Override
    public RentalResponseDto approveApplication(Long landlordId, Long applicationId) {
        // TODO: implement
        return null;
    }

    @Override
    public RentalResponseDto rejectApplication(Long landlordId, Long applicationId, RejectRentalDto dto) {
        // TODO: implement
        return null;
    }
}
