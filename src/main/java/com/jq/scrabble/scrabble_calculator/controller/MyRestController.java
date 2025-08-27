package com.jq.scrabble.scrabble_calculator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
    @GetMapping("/api/hello")
    public String hello(){
        return "Hello World";
    }
}
