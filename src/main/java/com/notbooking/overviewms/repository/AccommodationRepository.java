package com.notbooking.overviewms.repository;

import com.notbooking.overviewms.dto.params.AccommodationFilterParams;
import com.notbooking.overviewms.model.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE " +
            "LOWER(a.name) LIKE %:name% " +
            "AND a.maxGuests >= :guests " +
            "AND a.minGuests <= :guests " +
            "AND NOT EXISTS (SELECT r FROM Reservation r " +
            "               WHERE r.accommodation = a " +
            "               AND (:fromDate BETWEEN r.dateFrom AND r.dateTo " +
            "               OR :toDate BETWEEN r.dateFrom AND r.dateTo " +
            "               OR r.dateFrom BETWEEN :fromDate AND :toDate " +
            "               OR r.dateTo BETWEEN :fromDate AND :toDate)) " +
            "AND NOT EXISTS (SELECT u FROM Unavailability u " +
            "               WHERE u.accommodation = a " +
            "               AND (:fromDate BETWEEN u.dateFrom AND u.dateTo " +
            "               OR :toDate BETWEEN u.dateFrom AND u.dateTo " +
            "               OR u.dateFrom BETWEEN :fromDate AND :toDate " +
            "               OR u.dateTo BETWEEN :fromDate AND :toDate))")
    List<Accommodation> filterByParams(
            @Param("name") String name,
            @Param("guests") int guests,
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate);
}
