package com.smartrent.property.repository;

import com.smartrent.property.model.Property;
import com.smartrent.property.model.PropertyStatus;
import com.smartrent.property.model.PropertyType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    Page<Property> findByCityAndStatus(String city, PropertyStatus status, Pageable pageable);
    Page<Property> findByLandlordId(Long landlordId, Pageable pageable);
    List<Property> findByStatus(PropertyStatus status);

    @Query("SELECT p FROM Property p WHERE (:city IS NULL OR p.city = :city) AND p.status = 'APPROVED' AND (:minRent IS NULL OR p.monthlyRent >= :minRent) AND (:maxRent IS NULL OR p.monthlyRent <= :maxRent) AND (:type IS NULL OR p.type = :type)")
    Page<Property> searchProperties(String city, BigDecimal minRent, BigDecimal maxRent, PropertyType type, Pageable pageable);
}
