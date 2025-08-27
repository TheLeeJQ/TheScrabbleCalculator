package com.jq.scrabble.scrabble_calculator.repo;

import com.jq.scrabble.scrabble_calculator.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepo extends JpaRepository<Score, Long> {
    // If you want to fetch *all* in that order:
    List<Score> findAllByOrderByScoreDescCreatedAtAsc();
}
