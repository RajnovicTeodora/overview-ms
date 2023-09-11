package com.notbooking.overviewms.service;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.dto.params.AccommodationFilterParams;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.model.Accomodation;
import com.notbooking.overviewms.model.Reservation;
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
import java.util.stream.Collectors;

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
        return accommodationRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    }

//    public List<Accomodation> filterAll(AccommodationFilterParams params) {
//        return accommodationRepository.findByMaxGuestGreaterThanEqualAndMinGuestLessThanEqualAndNameIgnoreCaseContainingAndDateFromLessThanEqualAndDateToGreaterThanEqual(params.getGuests(), params.getGuests(), params.getCountry(), params.getFromDate(), params.getToDate());
//    }

    public boolean checkOverlap(Reservation r1, Date from, Date to) {
        return r1.getDateFrom().toInstant().isBefore(from.toInstant())
                && r1.getDateTo().toInstant().isAfter(to.toInstant());
    }

    public List<Accomodation> filterAll(
            AccommodationFilterParams params
    ) throws ParseException {
        if (params.getGuests() == 0 && params.getFromDate().isEmpty() &&
                params.getToDate().isEmpty() && params.getCity().isEmpty() && params.getAddress().isEmpty()) {
            return getAllAccommodations();
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

        if (params.getToDate().isEmpty() && params.getFromDate().isEmpty()) {

            if (params.getGuests() != 0)
                return accommodationRepository.findByMinGuestLessThanEqualAndMaxGuestGreaterThanEqual(
                                params.getGuests(),
                                params.getGuests())
                        .stream().filter(accomodation ->
                                accomodation.getAddres().getCity().toLowerCase().contains(params.getCity().toLowerCase())
                                        && accomodation.getAddres().getStreet().toLowerCase().contains(params.getAddress().toLowerCase())
                        )
                        .collect(Collectors.toList());
            else
                return accommodationRepository.findAll()
                        .stream().filter(accomodation ->
                                accomodation.getAddres().getCity().toLowerCase().contains(params.getCity().toLowerCase())
                                        && accomodation.getAddres().getStreet().toLowerCase().contains(params.getAddress().toLowerCase())
                        )
                        .collect(Collectors.toList());
        } else {
            var from = formatter.parse(params.getFromDate());
            var to = formatter.parse(params.getToDate());

            if (params.getGuests() != 0)
                return accommodationRepository.findByMinGuestLessThanEqualAndMaxGuestGreaterThanEqual(
                                params.getGuests(),
                                params.getGuests())
                        .stream().filter(accomodation ->
                                reservationRepository.findByAccomodationId(accomodation.getId())
                                        .stream().noneMatch(reservation -> checkOverlap(reservation, from, to) && (accomodation.getAddres().getCity().toLowerCase().contains(params.getCity().toLowerCase())
                                                && accomodation.getAddres().getStreet().toLowerCase().contains(params.getAddress().toLowerCase())))
                        ).collect(Collectors.toList());

            else
                return accommodationRepository.findAll()
                        .stream().filter(accomodation ->
                                reservationRepository.findByAccomodationId(accomodation.getId())
                                        .stream().noneMatch(reservation -> checkOverlap(reservation, from, to) && (accomodation.getAddres().getCity().toLowerCase().contains(params.getCity().toLowerCase())
                                                && accomodation.getAddres().getStreet().toLowerCase().contains(params.getAddress().toLowerCase())))
                        ).collect(Collectors.toList());
        }

    }
}
