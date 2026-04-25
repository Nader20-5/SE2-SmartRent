package com.smartrent.rental.repository;

import com.smartrent.rental.model.ApplicationStatus;
import com.smartrent.rental.model.RentalApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalApplicationRepository extends JpaRepository<RentalApplication, Long> {
    List<RentalApplication> findByTenantId(Long tenantId);
    List<RentalApplication> findByLandlordId(Long landlordId);
    boolean existsByTenantIdAndPropertyIdAndStatus(Long tenantId, Long propertyId, ApplicationStatus status);
    Page<RentalApplication> findByPropertyIdAndStatus(Long propertyId, ApplicationStatus status, Pageable pageable);
}
