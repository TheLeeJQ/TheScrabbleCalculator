package com.jq.scrabble.scrabble_calculator.controller;

import com.jq.scrabble.scrabble_calculator.domain.LetterPoints;
import com.jq.scrabble.scrabble_calculator.dto.ScoreRequest;
import com.jq.scrabble.scrabble_calculator.dto.ScoreResponse;
import com.jq.scrabble.scrabble_calculator.service.ScoreService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoreControllerTest {

    @ParameterizedTest
    @CsvSource({
            "Q U I Z, 22",
            "A B C, 7",
            "K, 6",
            "J A V A, 14",
            "Z, 10"
    })
    void calculateScore(String tilesCsv, int expectedScore) {
        List<String> tiles = Arrays.asList(tilesCsv.split(" "));

        /*
        // Arrange
        LetterPoints lp = new LetterPoints();
        ScoreService ss = new ScoreService();
        ScoreController controller = new ScoreController(lp,); // constructor injection
        ScoreRequest request = new ScoreRequest(tiles);

        // Act
        ScoreResponse response = controller.calculateScore(request);

        // Assert
        assertEquals(expectedScore, response.score()); // Q=10 + U=1 + I=1 + Z=10
        */
    }
}