package com.notbooking.overviewms.model;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(value = "accomodation")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Accomodation {
    @Id
    private String id;
    private String name;
    private List<String> listPhotos; //photos
    private Address addres;
    private boolean automaticApproval;
    private String benefits; // List<string>
    private int maxGuest;
    private int minGuest;
    private boolean isDeleted;
    private Host host;



    //private String description;

    private List<Score> scores;


    private double averageScore = 0;


    public void calculateAverageScore() {
        this.averageScore = scores.stream()
                .map(Score::getScore)
                .reduce(0, Integer::sum);
    }

}
