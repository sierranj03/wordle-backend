package com.sierrajackson.wordlebackend.controller;

import com.sierrajackson.wordlebackend.service.GuessService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guess")
public class GuessController {

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    @GetMapping
    public String getDailyWord() {
        return guessService.getDailyWord();
    }

}
