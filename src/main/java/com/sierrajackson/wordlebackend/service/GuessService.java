package com.sierrajackson.wordlebackend.service;

import com.sierrajackson.wordlebackend.dto.GuessDto;
import com.sierrajackson.wordlebackend.dto.GuessResponseDto;
import com.sierrajackson.wordlebackend.enums.LetterStatus;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class GuessService {

    private final ZoneId TIMEZONE = ZoneId.of("America/New_York");

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

    public GuessResponseDto guess(GuessDto guessDto) {
        if (!validateWord(guessDto.word())) {
            return new GuessResponseDto(false, null);
        }

        String guessedWord = guessDto.word().toLowerCase();
        String dailyWord = getDailyWord();
        LetterStatus[] statuses = new LetterStatus[5];

        for (int i = 0; i < statuses.length; i++) {
            if (guessedWord.charAt(i) == dailyWord.charAt(i)) {
                statuses[i] = LetterStatus.CORRECT;
            } else if (dailyWord.contains(String.valueOf(guessedWord.charAt(i)))) {
                statuses[i] = LetterStatus.IN_WORD;
            } else {
                statuses[i] = LetterStatus.INCORRECT;
            }
        }

        return new GuessResponseDto(true, statuses);
    }

    private boolean validateWord(String word) {
        String lowercaseWord = word.toLowerCase();
        return answers.contains(lowercaseWord) || guesses.contains(lowercaseWord);
    }

    private String getDailyWord() {
        ZonedDateTime today = LocalDate.now(TIMEZONE).atStartOfDay(TIMEZONE);
        int index = Math.floorMod(today.hashCode(), answers.size());
        return answers.get(index);
    }

}
