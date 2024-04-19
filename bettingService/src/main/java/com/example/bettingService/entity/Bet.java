package com.example.bettingService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name="bet")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="bet_value_creator")
    private float betValueCreator;
    @Column(name="bet_value_taker")
    private float betValueTaker;
    @Column(name="bet_on_home_team_creator")
    private boolean betOnHomeTeamCreator;
    @Enumerated(EnumType.STRING)
    @Column(name="status")
    private BetStatus betStatus;
    @Column(name="payout_creator")
    private float payoutCreator;
    @Column(name="payout_taker")
    private float payoutTaker;

    //EXTERNAL IDS
    @Column(name="bet_creator_id")
    private Long betCreatorId;
    @Column(name="bet_taker_id")
    private Long betTakerId;
    @Column(name="game_id")
    private Long gameId;
}