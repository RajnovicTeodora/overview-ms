package com.notbooking.overviewms.service;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.dto.params.AccommodationFilterParams;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.model.Accommodation;
import com.notbooking.overviewms.model.Address;
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

    @Autowired
    private AddressService addressService;

    public Accommodation createAccommodation(AccommodationDTO accommodationRequest) {
        Accommodation accommodation = accommodationMapper.toModel(accommodationRequest);
        return accommodationRepository.save(accommodation);
    }

    public List<Accommodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public Accommodation findById(Long id) {
        Accommodation accommodation = accommodationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        accommodation.setAddress(addressService.findById(accommodation.getAddress().getId()));
        return accommodation;
    }

    public List<Accommodation> filterAll(AccommodationFilterParams params) {
        return accommodationRepository.filterByParams(params);
    }
}