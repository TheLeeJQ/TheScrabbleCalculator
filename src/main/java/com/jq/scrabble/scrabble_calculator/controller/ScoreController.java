package com.jq.scrabble.scrabble_calculator.controller;

import com.jq.scrabble.scrabble_calculator.domain.LetterPoints;
import com.jq.scrabble.scrabble_calculator.dto.ScoreRequest;
import com.jq.scrabble.scrabble_calculator.dto.ScoreResponse;
import com.jq.scrabble.scrabble_calculator.service.ScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/") //Dev Mode
public class ScoreController {
    LetterPoints lp;
    ScoreService ss;

    public ScoreController(LetterPoints lp, ScoreService ss) {
        this.lp = lp;
        this.ss = ss;
    }

    @GetMapping("/getAll")
    public void getAll(){
        System.out.println(ss.top10().get(0).getWord());
        System.out.println(ss.top10().get(0).getScore());
    }

    @PostMapping("/score")
    public ScoreResponse calculateScore(@RequestBody ScoreRequest request){
        //Read the tiles
        List<String> tiles = request.inputTiles();
        System.out.println(tiles.toString());
        int score = tiles.stream().mapToInt(lp::valueOf).sum();
        System.out.println("score: " + score);
        System.out.println(ss.top10());
        return new ScoreResponse(score);
    }

}
