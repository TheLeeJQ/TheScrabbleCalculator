package com.jq.scrabble.scrabble_calculator.dto;

import java.util.List;

public record ScoreRequest(
        List<String> inputTiles
) {}
