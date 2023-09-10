package com.notbooking.overviewms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.notbooking.overviewms.model.Reservation;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    @Query("{" +
            "$or: [" +
            "   { $and: [ { dateFrom: { $lte: :toDate }, dateTo: { $gte: :fromDate } }, { isCancelled: false } ] }, " +
            "   { $and: [ { dateFrom: { $gte: :fromDate, $lte: :toDate } }, { isCancelled: false } ] }" +
            "]" +
            "}")
    List<String> findOverlappingReservationIds(
            @Param("fromDate") Date fromDate,
            @Param("toDate") Date toDate
    );
}
