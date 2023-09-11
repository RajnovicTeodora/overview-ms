package com.notbooking.overviewms.mapper;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.model.Accomodation;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccommodationMapper {

    private final AddressMapper addressMapper;

    public AccommodationMapper(AddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public Accomodation toModel(AccommodationDTO accommodationRequest) {
        return Accomodation.builder()
                .name(accommodationRequest.getName())
                // .description(accommodationRequest.getDescription())
                .addres(addressMapper.toModel(accommodationRequest.getAddress()))
                .listPhotos(accommodationRequest.getPhotos())
                .benefits(accommodationRequest.getBenefits())
                .maxGuest(accommodationRequest.getMaxGuests())
                .minGuest(accommodationRequest.getMinGuests())
                .automaticApproval(accommodationRequest.isAutomaticApproval())
                .build();
    }

    public AccommodationDTO toDto(Accomodation accommodation) {
        return AccommodationDTO.builder()
                .id(accommodation.getId())
                .name(accommodation.getName())
                //.description(accommodation.getDescription())
                .photos(accommodation.getListPhotos())
                .benefits(accommodation.getBenefits())
                .maxGuests(accommodation.getMaxGuest())
                .minGuests(accommodation.getMinGuest())
                //.averageScore(accommodation.getAverageScore())
                .automaticApproval(accommodation.isAutomaticApproval())
                .address(addressMapper.toDto(accommodation.getAddres()))
                .deleted(accommodation.isDeleted())
                .build();
    }

    public List<AccommodationDTO> toDto(List<Accomodation> accommodations) {
        return accommodations.stream().map(this::toDto).collect(Collectors.toList());
    }
}
