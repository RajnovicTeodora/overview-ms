package com.notbooking.overviewms.dto.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationFilterParams {

    private String city;
    private String address;
    private int guests;
    private String fromDate;
    private String toDate;

}
