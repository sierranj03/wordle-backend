package com.sierrajackson.wordlebackend.dto;

import com.sierrajackson.wordlebackend.enums.LetterStatus;

public record GuessResponseDto(boolean valid, LetterStatus[] statuses) {}
