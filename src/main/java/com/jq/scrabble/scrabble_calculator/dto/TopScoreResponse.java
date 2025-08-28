package com.jq.scrabble.scrabble_calculator.dto;

import com.jq.scrabble.scrabble_calculator.entity.Score;

import java.util.List;

public record TopScoreResponse(
        List<Score> topScore
) {
}
