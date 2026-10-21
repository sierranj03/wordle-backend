package com.sierrajackson.wordlebackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guess")
public class GuessController {

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

}
