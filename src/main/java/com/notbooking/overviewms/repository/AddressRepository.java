package com.notbooking.overviewms.repository;

import com.notbooking.overviewms.model.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends MongoRepository<Address, String> {

}
