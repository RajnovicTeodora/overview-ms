package com.notbooking.overviewms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "guest")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Guest {

    @Id
    private String id;
    private String username;
    private int  canceldReservations;


}
