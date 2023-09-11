package com.notbooking.overviewms.repository;

import com.notbooking.overviewms.model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {

    List<Reservation> findByAccomodationId(String accomodationId);
}
