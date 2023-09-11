package com.notbooking.overviewms.repository;

import com.notbooking.overviewms.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreRepository extends MongoRepository<Score, String> {
}
