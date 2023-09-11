package com.notbooking.overviewms.repository;

import com.notbooking.overviewms.model.Accomodation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccomodationRepository extends MongoRepository<Accomodation, String> {

//    @Query("SELECT a FROM Accommodation a WHERE " +
//            "LOWER(a.name) LIKE %:name% " +
//            "AND a.maxGuests >= :guests " +
//            "AND a.minGuests <= :guests " +
//            "AND NOT EXISTS (SELECT r FROM Reservation r " +
//            "               WHERE r.accommodation = a " +
//            "               AND (:fromDate BETWEEN r.dateFrom AND r.dateTo " +
//            "               OR :toDate BETWEEN r.dateFrom AND r.dateTo " +
//            "               OR r.dateFrom BETWEEN :fromDate AND :toDate " +
//            "               OR r.dateTo BETWEEN :fromDate AND :toDate)) " +
//            "AND NOT EXISTS (SELECT u FROM Unavailability u " +
//            "               WHERE u.accommodation = a " +
//            "               AND (:fromDate BETWEEN u.dateFrom AND u.dateTo " +
//            "               OR :toDate BETWEEN u.dateFrom AND u.dateTo " +
//            "               OR u.dateFrom BETWEEN :fromDate AND :toDate " +
//            "               OR u.dateTo BETWEEN :fromDate AND :toDate))")
//    List<Accomodation> filterByParams(
//            @Param("name") String name,
//            @Param("guests") int guests,
//            @Param("fromDate") String fromDate,
//            @Param("toDate") String toDate);


    List<Accomodation> findByMinGuestLessThanEqualAndMaxGuestGreaterThanEqual(
            int maxGuest,
            int minGuest
    );

    List<Accomodation> findByMinGuestLessThanEqualAndMaxGuestGreaterThanEqualAndIdNotIn(
            int maxGuest,
            int minGuest,
            List<String> reservationIds
    );
}

