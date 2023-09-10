package com.notbooking.overviewms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Reservation extends DateRange {
    private int guestNumber;
    private Guest guest;  //da imam samo jednog gosta koji je rezervisao ovo u jednom trebutku
    private String accomodationId;
    private boolean isDeleted;
    private boolean isCancled;

}