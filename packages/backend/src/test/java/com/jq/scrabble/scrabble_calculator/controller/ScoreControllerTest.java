package com.jq.scrabble.scrabble_calculator.controller;

import com.jq.scrabble.scrabble_calculator.domain.LetterPoints;
import com.jq.scrabble.scrabble_calculator.dto.ScoreRequest;
import com.jq.scrabble.scrabble_calculator.dto.ScoreResponse;
import com.jq.scrabble.scrabble_calculator.dto.TopScoreResponse;
import com.jq.scrabble.scrabble_calculator.entity.Score;
import com.jq.scrabble.scrabble_calculator.service.ScoreService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ScoreControllerTest {
    private LetterPoints lp;        // mock
    private ScoreController controller;
    private ScoreService ss;

    @BeforeEach
    void setUp() {
        lp = Mockito.mock(LetterPoints.class);
        ss = Mockito.mock(ScoreService.class);
        controller = new ScoreController(lp,ss);
    }

    @Test
    void getTopTenScores() {
        //Arrange
        ScoreService ss = mock(ScoreService.class);
        LetterPoints lp = mock(LetterPoints.class);
        List<Score> mockScores =
                java.util.stream.IntStream.rangeClosed(1, 10)
                        .mapToObj(i -> new Score("word" + i, i * 10))
                        .toList();
        when(ss.getTopTenScores()).thenReturn(mockScores);
        ScoreController controller = new ScoreController(lp, ss);

        //Action
        TopScoreResponse tsr = controller.getTopTenScores();

        //Assert
        assertEquals(10, tsr.topScore().size());
    }



    @Test
    @DisplayName("calculateScore sums tiles (A=1, B=3, C=3) → 7")
    void calculateScore_sumsTiles() {
        // Arrange
        ScoreRequest req = mock(ScoreRequest.class);
        when(req.inputTiles()).thenReturn(List.of("A", "B", "C"));
        when(lp.valueOf("A")).thenReturn(1);
        when(lp.valueOf("B")).thenReturn(3);
        when(lp.valueOf("C")).thenReturn(3);

        // Act
        ScoreResponse res = controller.calculateScore(req);

        // Assert
        assertEquals(7, res.score()); // or res.score() if it's a record
        verify(lp).valueOf("A");
        verify(lp).valueOf("B");
        verify(lp).valueOf("C");
        verifyNoMoreInteractions(lp);
    }

    @Test
    @DisplayName("saveScore: sums tiles, saves Score(word joined), returns ScoreResponse")
    void saveScore_savesAndReturnsScore() {
        // Arrange
        ScoreRequest req = mock(ScoreRequest.class);
        List<String> tiles = List.of("H", "E", "L", "L", "O");
        when(req.inputTiles()).thenReturn(tiles);

        when(lp.valueOf("H")).thenReturn(4);
        when(lp.valueOf("E")).thenReturn(1);
        when(lp.valueOf("L")).thenReturn(1);
        when(lp.valueOf("O")).thenReturn(1);

        // Act
        ScoreResponse res = controller.saveScore(req);

        // Assert
        // Returned response score = 4 + 1 + 1 + 1 + 1 = 8
        assertEquals(8, res.score()); // use res.score() if ScoreResponse is a record

        // Verify saved entity
        ArgumentCaptor<Score> captor = ArgumentCaptor.forClass(Score.class);
        verify(ss).saveScore(captor.capture());
        Score saved = captor.getValue();
        assertEquals("HELLO", saved.getWord());
        assertEquals(8, saved.getScore());

        // Ensure we called valueOf for each tile
        verify(lp, times(2)).valueOf("L");
        verify(lp).valueOf("H");
        verify(lp).valueOf("E");
        verify(lp).valueOf("O");
        verifyNoMoreInteractions(lp, ss);
    }
}