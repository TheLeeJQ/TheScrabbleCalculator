package com.jq.scrabble.scrabble_calculator.controller;

import com.jq.scrabble.scrabble_calculator.dto.ScoreRequest;
import com.jq.scrabble.scrabble_calculator.dto.ScoreResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/") //Dev Mode
public class ScoreController {

    @PostMapping("/score")
    public ScoreResponse calculateScore(@RequestBody ScoreRequest request){
        //Read the tiles
        List<String> tiles = request.inputTiles();

        String word = String.join("", tiles).trim();

        System.out.println("Print word: " + word);
        return new ScoreResponse(word);
    }

}
