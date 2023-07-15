package com.notbooking.overviewms.service;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.model.Accommodation;
import com.notbooking.overviewms.repository.AccommodationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;


    private final AccommodationMapper accommodationMapper;

    public AccommodationService(AccommodationRepository accommodationRepository,
                                AccommodationMapper accommodationMapper) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationMapper = accommodationMapper;
    }

    public AccommodationDTO createAccommodation(AccommodationDTO accommodationRequest) {
        Accommodation accommodation = accommodationMapper.toModel(accommodationRequest);
        return accommodationMapper.toDto(accommodationRepository.save(accommodation));
    }

    public List<AccommodationDTO> getAllAccommodations() {
        List<Accommodation> accommodations = accommodationRepository.findAll();
        return accommodationMapper.toDto(accommodations);
    }

    public AccommodationDTO findById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        return accommodationMapper.toDto(accommodation);
    }
}