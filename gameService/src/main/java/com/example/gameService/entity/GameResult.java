package com.example.gameService.entity;

import jakarta.persistence.*;
import lombok.*;

// @Getter
// @Setter

@Getter
@Setter
@Entity
@Table(name="game_result")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
public class GameResult {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="goals_home_team")
    private Integer goalsHomeTeam;
    @Column(name="goals_away_team")
    private Integer goalsAwayTeam;

    //Foreign IDS
    @Column(name="game_id")
    private Long gameId;
}
