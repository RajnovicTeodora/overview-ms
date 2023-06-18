package com.notbooking.overviewms.mapper;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.model.Accommodation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccommodationMapper implements DefaultMapper<AccommodationDTO, Accommodation> {

    public Accommodation toModel(AccommodationDTO accommodationRequest) {
        return Accommodation.builder()
                .name(accommodationRequest.getName())
                .description(accommodationRequest.getDescription())
                .address(accommodationRequest.getAddress())
                .photos(accommodationRequest.getPhotos())
                .benefits(accommodationRequest.getBenefits())
                .maxGuests(accommodationRequest.getMaxGuests())
                .minGuests(accommodationRequest.getMinGuests())
                .automaticApproval(accommodationRequest.isAutomaticApproval())
                .build();
    }

    public AccommodationDTO toDto(Accommodation accommodation) {
        return AccommodationDTO.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                .description(accommodation.getDescription())
                .address(accommodation.getAddress())
                .photos(accommodation.getPhotos())
                .benefits(accommodation.getBenefits())
                .maxGuests(accommodation.getMaxGuests())
                .minGuests(accommodation.getMinGuests())
                .averageScore(accommodation.getAverageScore())
                .automaticApproval(accommodation.isAutomaticApproval())
                .deleted(accommodation.isDeleted())
                .build();
    }

    public List<AccommodationDTO> toDto(List<Accommodation> accommodations) {
        return accommodations.stream().map(this::toDto).collect(Collectors.toList());
    }
}
