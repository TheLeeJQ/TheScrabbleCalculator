package com.jq.scrabble.scrabble_calculator.service;

import com.jq.scrabble.scrabble_calculator.entity.Score;
import com.jq.scrabble.scrabble_calculator.repo.ScoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {
    private final ScoreRepo repo;
    public ScoreService(ScoreRepo repo) { this.repo = repo; }

    public List<Score> top10() {
        return repo.findAllByOrderByScoreDescCreatedAtAsc();
    }
}
