package com.notbooking.overviewms.service;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.dto.params.AccommodationFilterParams;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.model.Accomodation;
import com.notbooking.overviewms.repository.AccomodationRepository;
import com.notbooking.overviewms.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class AccommodationService {

    private final AccomodationRepository accommodationRepository;


    private final AccommodationMapper accommodationMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private ReservationRepository reservationRepository;

    public AccommodationService(AccomodationRepository accommodationRepository,
                                AccommodationMapper accommodationMapper) {
        this.accommodationRepository = accommodationRepository;
        this.accommodationMapper = accommodationMapper;
    }

    public Accomodation createAccommodation(AccommodationDTO accommodationRequest) {
        Accomodation accommodation = accommodationMapper.toModel(accommodationRequest);
        return accommodationRepository.save(accommodation);
    }

    public List<Accomodation> getAllAccommodations() {
        return accommodationRepository.findAll();
    }

    public Accomodation findById(String id) {
        Accomodation accommodation = accommodationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        accommodation.setAddres(addressService.findById(accommodation.getAddres().getId()));
        return accommodation;
    }

//    public List<Accomodation> filterAll(AccommodationFilterParams params) {
//        return accommodationRepository.findByMaxGuestGreaterThanEqualAndMinGuestLessThanEqualAndNameIgnoreCaseContainingAndDateFromLessThanEqualAndDateToGreaterThanEqual(params.getGuests(), params.getGuests(), params.getCountry(), params.getFromDate(), params.getToDate());
//    }


    public List<Accomodation> filterAll(
            AccommodationFilterParams params
    ) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault());

        Date from = formatter.parse(params.getFromDate());
        Date to = formatter.parse(params.getToDate());
        // Prepare a list of reservation IDs that overlap with the date range
        List<String> overlappingReservationIds = reservationRepository.findOverlappingReservationIds(from, to);

        // Call the repository method to find available accommodations
        if (!overlappingReservationIds.isEmpty()) {
            return accommodationRepository.findByMinGuestLessThanEqualAndMaxGuestGreaterThanEqualAndNameIsLikeAndIdNotIn(
                    params.getGuests(),
                    params.getGuests(),
                    "%" + params.getCountry().toLowerCase() + "%",
                    overlappingReservationIds
            );
        } else {
            return accommodationRepository.findByMinGuestLessThanEqualAndMaxGuestGreaterThanEqualAndNameIsLikeAndIdNotInAndIdNotIn(
                    params.getGuests(),
                    params.getGuests(),
                    "%" + params.getCountry().toLowerCase() + "%"
            );
        }
    }
}
