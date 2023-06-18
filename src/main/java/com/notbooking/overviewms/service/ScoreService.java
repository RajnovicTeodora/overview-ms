package com.notbooking.overviewms.service;

import com.notbooking.overviewms.repository.ScoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoreService {

    private final ScoreRepository scoreRepository;

}
