package com.notbooking.overviewms.controller;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/accommodation")
public class AccommodationController {

    @Autowired
    private final AccommodationService accommodationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccommodationDTO> createAccommodation(@RequestBody AccommodationDTO accommodationRequest) {
        AccommodationDTO accommodation = accommodationService.createAccommodation(accommodationRequest);
        if (accommodation != null)
            return new ResponseEntity<>(accommodation, HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<AccommodationDTO>> getAllAccommodations() {
        return new ResponseEntity<>(accommodationService.getAllAccommodations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AccommodationDTO> findById(@PathVariable Long id) {
        return new ResponseEntity<>(accommodationService.findById(id), HttpStatus.OK);
    }

}
