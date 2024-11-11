package com.example.ui.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class GameResponse {
    private Long id;
    private String homeTeam;
    private String awayTeam;
    private LocalDateTime startTime;
    private String league;
    private GameResultResponse gameResultResponse;
    @Getter
    @Setter
    @AllArgsConstructor
    private static class GameResultResponse {
        private Long id;
        private Integer goalsHomeTeam;
        private Integer goalsAwayTeam;
    }
}

