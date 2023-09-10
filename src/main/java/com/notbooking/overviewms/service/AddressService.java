package com.notbooking.overviewms.service;

import com.notbooking.overviewms.dto.AddressDTO;
import com.notbooking.overviewms.mapper.AddressMapper;
import com.notbooking.overviewms.model.Address;
import com.notbooking.overviewms.repository.AddressRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    public Address createAddress(AddressDTO addressRequest) {
        Address address = addressMapper.toModel(addressRequest);
        return addressRepository.save(address);
    }

    public Address findById(String id) {
        return addressRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
}
