package com.sierrajackson.wordlebackend.controller;

import com.sierrajackson.wordlebackend.dto.GuessDto;
import com.sierrajackson.wordlebackend.dto.GuessResponseDto;
import com.sierrajackson.wordlebackend.service.GuessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guess")
public class GuessController {

    private final GuessService guessService;

    public GuessController(GuessService guessService) {
        this.guessService = guessService;
    }

    @PostMapping
    public ResponseEntity<GuessResponseDto> guess(@RequestBody GuessDto guessDto) {
        return ResponseEntity.ok(guessService.guess(guessDto));
    }

}
