package com.notbooking.overviewms.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Document(value = "score")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Score {
    @Id
    private String Id;
    private int score;
    private Date date;
    private boolean isDeleted;
    private Guest guest;
    private Host host;
    private Accomodation accomodation;

}

