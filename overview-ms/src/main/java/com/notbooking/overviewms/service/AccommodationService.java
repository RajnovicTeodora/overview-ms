package com.notbooking.overviewms.service;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.model.Accommodation;
import com.notbooking.overviewms.repository.AccommodationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private AccommodationMapper accommodationMapper;

    public AccommodationDTO createAccommodation(AccommodationDTO accommodationRequest) {
        Accommodation accommodation = accommodationMapper.toModel(accommodationRequest);
        return accommodationMapper.toDto(accommodationRepository.save(accommodation));
    }

    public List<AccommodationDTO> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodationMapper.toDto(accommodations);
    }

    public AccommodationDTO updateAccommodation(AccommodationDTO accommodationRequest) {

        return createAccommodation(accommodationRequest);
    }
}