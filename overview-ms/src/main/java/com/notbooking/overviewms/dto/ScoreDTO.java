package com.notbooking.overviewms.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ScoreDTO extends DefaultDTO {

    private int score;
    private LocalDateTime date;
    private String guestId;
    private AccommodationDTO accommodation;

}
