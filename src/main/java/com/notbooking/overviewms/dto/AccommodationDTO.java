package com.notbooking.overviewms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AccommodationDTO extends DefaultDTO {

    private String name;
    private String description;
    private List<String> photos;
    private String benefits;
    private int maxGuests;
    private int minGuests;
    private boolean automaticApproval;
    private double averageScore;
    private AddressDTO address;

}
