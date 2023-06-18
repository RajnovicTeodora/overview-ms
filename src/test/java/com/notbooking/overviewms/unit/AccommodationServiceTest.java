package com.notbooking.overviewms.unit;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.model.Accommodation;
import com.notbooking.overviewms.repository.AccommodationRepository;
import com.notbooking.overviewms.service.AccommodationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-test.properties")
class AccommodationServiceTest {

    @Autowired
    private AccommodationService accommodationService;

    @MockBean
    private AccommodationRepository accommodationRepository;

    @Test
    void testGetAllAccommodations() {
        // Mocking repository behavior
        List<Accommodation> accommodationList = new ArrayList<>();
        accommodationList.add(new Accommodation());
        when(accommodationRepository.findAll()).thenReturn(accommodationList);

        List<AccommodationDTO> resultDTOs = accommodationService.getAllAccommodations();

        // Verify
        verify(accommodationRepository).findAll();
        Assertions.assertEquals(accommodationList.size(), resultDTOs.size());
    }

}
