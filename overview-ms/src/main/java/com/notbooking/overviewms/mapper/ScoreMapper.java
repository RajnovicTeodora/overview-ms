package com.notbooking.overviewms.mapper;

import com.notbooking.overviewms.dto.ScoreDTO;
import com.notbooking.overviewms.model.Score;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ScoreMapper implements DefaultMapper<ScoreDTO, Score> {

    private final AccommodationMapper accommodationMapper;

    public ScoreMapper(AccommodationMapper accommodationMapper) {
        this.accommodationMapper = accommodationMapper;
    }

    public Score toModel(ScoreDTO scoreRequest) {
        return Score.builder()
                .score(scoreRequest.getScore())
                .date(scoreRequest.getDate())
                .guestId(scoreRequest.getGuestId())
                .build();
    }

    public ScoreDTO toDto(Score score) {
        return ScoreDTO.builder()
                .id(score.getId())
                .score(score.getScore())
                .date(score.getDate())
                .guestId(score.getGuestId())
                .accommodation(accommodationMapper.toDto(score.getAccommodation()))
                .deleted(score.isDeleted())
                .build();
    }

    public List<ScoreDTO> toDto(List<Score> scores) {
        return scores.stream().map(this::toDto).collect(Collectors.toList());
    }
}
