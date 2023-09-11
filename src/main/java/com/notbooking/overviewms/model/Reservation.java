package com.notbooking.overviewms.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class Reservation extends DateRange {
    private int guestNumber;
    private Guest guest;  //da imam samo jednog gosta koji je rezervisao ovo u jednom trebutku
    private String accomodationId;
    private boolean isDeleted;
    private boolean isCancled;

}