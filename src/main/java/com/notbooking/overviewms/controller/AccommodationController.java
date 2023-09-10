package com.notbooking.overviewms.controller;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.dto.params.AccommodationFilterParams;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/accommodation")
public class AccommodationController {

    @Autowired
    private final AccommodationService accommodationService;

    private final AccommodationMapper accommodationMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationRequest) {
        var accommodation = accommodationService.createAccommodation(accommodationRequest);
        if (accommodation != null)
            return new ResponseEntity<>(accommodationMapper.toDto(accommodation), HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations() {
        var accommodations = accommodationService.getAllAccommodations();
        return new ResponseEntity<>(accommodationMapper.toDto(accommodations), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccommodationDTO> findById(@PathVariable String id) {
        var accommodation = accommodationService.findById(id);
        return new ResponseEntity<>(accommodationMapper.toDto(accommodation), HttpStatus.OK);
    }

    @GetMapping("/filter")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AccommodationDTO>> filterAll(AccommodationFilterParams params) throws ParseException {
        var accommodations = accommodationService.filterAll(params);
        return new ResponseEntity<>(accommodationMapper.toDto(accommodations), HttpStatus.OK);
    }

}
