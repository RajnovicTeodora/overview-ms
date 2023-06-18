package com.notbooking.overviewms.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "scores")
@EqualsAndHashCode(callSuper = true)
public class Score extends DefaultModel {

    @Column(columnDefinition = "int")
    private int score;

    @Column(columnDefinition = "int")
    private LocalDateTime date;

    @Column(name = "guest_id")
    private String guestId;

    @ManyToOne
    @JoinColumn(name = "accommodation_id")
    private Accommodation accommodation;

}
