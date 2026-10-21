package com.sierrajackson.wordlebackend.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Service
public class GuessService {

    @Value("classpath:answers.txt")
    private Resource answersFile;
    private List<String> answers;

    @Value("classpath:guesses.txt")
    private Resource guessesFile;
    private List<String> guesses;

    @PostConstruct
    public void init() throws IOException {
        answers = Files.readAllLines(answersFile.getFilePath());
        guesses = Files.readAllLines(guessesFile.getFilePath());
    }

    public String getDailyWord() {
        return answers.getFirst();
    }

}
