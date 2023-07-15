package com.notbooking.overviewms;

import com.notbooking.overviewms.dto.AccommodationDTO;
import com.notbooking.overviewms.mapper.AccommodationMapper;
import com.notbooking.overviewms.model.Accommodation;
import com.notbooking.overviewms.repository.AccommodationRepository;
import com.notbooking.overviewms.service.AccommodationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest(
        classes = OverviewMsApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.datasource.url=jdbc:tc:postgresql:11-alpine:///notBookingDBTest"}
)
@ActiveProfiles("test")
public class AccommodationTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccommodationTest.class);

    @Container
    private static final PostgreSQLContainer POSTGRES_SQL_CONTAINER;

    static {

        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>("postgres:11.1")
                .withUsername("postgres")
                .withPassword("postgres")
                .withDatabaseName("notBookingDBTest")
                .withPrivilegedMode(true);


        POSTGRES_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void overrideTestProperties(DynamicPropertyRegistry registry) {

        System.out.println(POSTGRES_SQL_CONTAINER.getJdbcUrl());
        LOGGER.info("JDBC URL: {}", POSTGRES_SQL_CONTAINER.getJdbcUrl());
        registry.add("spring.datasource.url", () -> "jdbc:tc:postgresql:11-alpine:///notBookingDBTest?loggerLevel=OFF"); //POSTGRES_SQL_CONTAINER::getJdbcUrl);
        registry.add("javax.persistence.jdbc.url", () -> "jdbc:tc:postgresql:11-alpine:///notBookingDBTest?loggerLevel=OFF");
        registry.add("spring.datasource.username", POSTGRES_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_SQL_CONTAINER::getPassword);
        registry.add("spring.datasource.driverClassName", () -> "org.testcontainers.jdbc.ContainerDatabaseDriver");

        registry.add("integration-tests-db", POSTGRES_SQL_CONTAINER::getDatabaseName);

    }

    @Mock
    private AccommodationRepository accommodationRepository;

    private AccommodationService accommodationService;

    @BeforeEach
    void setUp() {
        AccommodationMapper accommodationMapper = new AccommodationMapper();
        this.accommodationService
                = new AccommodationService(accommodationRepository, accommodationMapper);
    }

    @Test
    public void givenAccommodations_whenGetEmployees_thenStatus200() {
        this.accommodationService.getAllAccommodations();
        verify(accommodationRepository).findAll();
    }
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