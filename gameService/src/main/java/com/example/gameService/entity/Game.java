package com.example.gameService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Entity
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="home_team")
    private String homeTeam;
    @Column(name="away_team")
    private String awayTeam;
    @Column(name="start_time")
    private LocalDateTime startTime;
    @Column(name="league")
    private String league;
}
