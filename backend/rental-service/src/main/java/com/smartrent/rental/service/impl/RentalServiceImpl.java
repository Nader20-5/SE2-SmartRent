package com.smartrent.rental.service.impl;

import com.smartrent.rental.client.PropertyServiceClient;
import com.smartrent.rental.client.UserServiceClient;
import com.smartrent.rental.client.dto.PropertySummaryDto;
import com.smartrent.rental.dto.*;
import com.smartrent.rental.exception.*;
import com.smartrent.rental.mapper.RentalMapper;
import com.smartrent.rental.model.ApplicationDocument;
import com.smartrent.rental.model.ApplicationStatus;
import com.smartrent.rental.model.DocumentType;
import com.smartrent.rental.model.RentalApplication;
import com.smartrent.rental.repository.ApplicationDocumentRepository;
import com.smartrent.rental.repository.RentalApplicationRepository;
import com.smartrent.rental.service.interfaces.IRentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RentalServiceImpl implements IRentalService {

    private final RentalApplicationRepository rentalApplicationRepository;
    private final ApplicationDocumentRepository applicationDocumentRepository;
    private final RentalMapper rentalMapper;
    private final UserServiceClient userServiceClient; // Only for checking user? Gateway gives role/id.
    private final PropertyServiceClient propertyServiceClient;

    @Value("${app.storage.base-path}")
    private String basePath;

    @Override
    @Transactional
    public RentalResponseDto createApplication(Long tenantId, CreateRentalDto dto) {
        // Verify property
        PropertySummaryDto property = propertyServiceClient.getPropertyById(dto.getPropertyId());
        if (!"APPROVED".equals(property.getStatus()) || Boolean.FALSE.equals(property.getIsAvailable())) {
            throw new InvalidApplicationStatusException("Property is not available or not approved");
        }

        // Duplicate check PENDING
        if (rentalApplicationRepository.existsByTenantIdAndPropertyIdAndStatus(tenantId, dto.getPropertyId(), ApplicationStatus.PENDING)) {
            throw new DuplicateApplicationException("You already have a pending application for this property");
        }

        // Duplicate check APPROVED
        if (rentalApplicationRepository.existsByTenantIdAndPropertyIdAndStatus(tenantId, dto.getPropertyId(), ApplicationStatus.APPROVED)) {
            throw new DuplicateApplicationException("You already have an approved application for this property");
        }

        RentalApplication application = rentalMapper.toEntity(dto);
        application.setTenantId(tenantId);
        application.setLandlordId(property.getLandlordId());
        application.setPropertyTitle(property.getTitle());
        application.setMonthlyRentSnapshot(property.getMonthlyRent());
        application.setStatus(ApplicationStatus.PENDING);

        application = rentalApplicationRepository.save(application);
        return rentalMapper.toResponseDto(application);
    }

    @Override
    @Transactional
    public RentalResponseDto uploadDocuments(Long tenantId, Long applicationId, DocumentType type, MultipartFile file) {
        RentalApplication application = rentalApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RentalApplicationNotFoundException("Rental application not found"));

        if (!application.getTenantId().equals(tenantId)) {
            throw new UnauthorizedRentalActionException("You are not authorized to upload documents for this application");
        }

        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidApplicationStatusException("Cannot upload documents. Application is not in PENDING status.");
        }

        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new DocumentUploadException("Invalid file");
        }

        String mimeType = file.getContentType();
        if (mimeType == null || (!mimeType.equals("application/pdf") && !mimeType.equals("image/jpeg") && !mimeType.equals("image/png"))) {
            throw new DocumentUploadException("Only PDF, JPEG, and PNG files are allowed");
        }

        String extension = "";
        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex > 0) {
            extension = originalFilename.substring(dotIndex);
        }

        String filename = UUID.randomUUID().toString() + extension;
        Path uploadDir = Paths.get(basePath);
        
        try {
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }
            File destinationFile = new File(uploadDir.toFile(), filename);
            file.transferTo(destinationFile);
        } catch (IOException e) {
            throw new DocumentUploadException("Failed to upload document: " + e.getMessage());
        }

        ApplicationDocument document = ApplicationDocument.builder()
                .application(application)
                .documentType(type)
                .fileName(originalFilename)
                .fileUrl("/uploads/" + filename)
                .fileSizeBytes(file.getSize())
                .build();

        applicationDocumentRepository.save(document);

        // Fetch application again to get updated documents list (since it's a new transaction save)
        return rentalMapper.toResponseDto(application);
    }

    @Override
    public List<RentalResponseDto> getTenantApplications(Long tenantId) {
        return rentalApplicationRepository.findByTenantId(tenantId).stream()
                .map(rentalMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public RentalResponseDto getApplicationById(Long requesterId, String requesterRole, Long applicationId) {
        RentalApplication application = rentalApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RentalApplicationNotFoundException("Rental application not found"));

        if (!"ADMIN".equals(requesterRole) && !application.getTenantId().equals(requesterId) && !application.getLandlordId().equals(requesterId)) {
            throw new UnauthorizedRentalActionException("You are not authorized to view this application");
        }

        return rentalMapper.toResponseDto(application);
    }

    @Override
    @Transactional
    public void withdrawApplication(Long tenantId, Long applicationId) {
        RentalApplication application = rentalApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RentalApplicationNotFoundException("Rental application not found"));

        if (!application.getTenantId().equals(tenantId)) {
            throw new UnauthorizedRentalActionException("You are not authorized to withdraw this application");
        }

        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidApplicationStatusException("Only PENDING applications can be withdrawn");
        }

        application.setStatus(ApplicationStatus.WITHDRAWN);
        rentalApplicationRepository.save(application);
    }

    @Override
    public List<RentalResponseDto> getLandlordApplications(Long landlordId) {
        return rentalApplicationRepository.findByLandlordId(landlordId).stream()
                .map(rentalMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public RentalResponseDto approveApplication(Long landlordId, Long applicationId) {
        RentalApplication application = rentalApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RentalApplicationNotFoundException("Rental application not found"));

        if (!application.getLandlordId().equals(landlordId)) {
            throw new UnauthorizedRentalActionException("You are not authorized to approve this application");
        }

        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidApplicationStatusException("Only PENDING applications can be approved");
        }

        application.setStatus(ApplicationStatus.APPROVED);
        application.setReviewedAt(LocalDateTime.now());
        
        application = rentalApplicationRepository.save(application);
        return rentalMapper.toResponseDto(application);
    }

    @Override
    @Transactional
    public RentalResponseDto rejectApplication(Long landlordId, Long applicationId, RejectRentalDto dto) {
        RentalApplication application = rentalApplicationRepository.findById(applicationId)
                .orElseThrow(() -> new RentalApplicationNotFoundException("Rental application not found"));

        if (!application.getLandlordId().equals(landlordId)) {
            throw new UnauthorizedRentalActionException("You are not authorized to reject this application");
        }

        if (application.getStatus() != ApplicationStatus.PENDING) {
            throw new InvalidApplicationStatusException("Only PENDING applications can be rejected");
        }

        application.setStatus(ApplicationStatus.REJECTED);
        application.setRejectionReason(dto.getRejectionReason());
        application.setReviewedAt(LocalDateTime.now());
        
        application = rentalApplicationRepository.save(application);
        return rentalMapper.toResponseDto(application);
    }
}
