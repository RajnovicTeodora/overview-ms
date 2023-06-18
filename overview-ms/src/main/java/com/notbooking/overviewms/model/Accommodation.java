package com.notbooking.overviewms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accommodations")
@EqualsAndHashCode(callSuper = true)
public class Accommodation extends DefaultModel {


    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address; // Todo maybe place the information inside a class

    @Column(name = "photos")
    private List<String> photos;

    @Column(name = "benefits")
    private List<String> benefits;

    @Column(name = "max_guests")
    private int maxGuests;

    @Column(name = "min_guests")
    private int minGuests;

    @Column(name = "automatic_approval")
    private boolean automaticApproval;

    @JsonIgnore
    @OneToMany(mappedBy = "accommodation")
    private List<Score> scores;

    @Builder.Default
    @Column(name = "average_score")
    private double averageScore = 0;

    public void calculateAverageScore() {
        this.averageScore = scores.stream()
                .map(Score::getScore)
                .reduce(0, Integer::sum);
    }

}
