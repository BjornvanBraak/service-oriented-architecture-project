package com.example.ui.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BetResponse {
    private Long id;
    private float betValueCreator;

    private float betValueTaker;

    private boolean betOnHomeTeamCreator;
//    @Enumerated(EnumType.STRING)
//    @Column(name="status")
    private String betStatus;
    private float payoutCreator;
    private float payoutTaker;

    //EXTERNAL IDS
    private Long betCreatorId;
    private Long betTakerId;
    private Long gameId;
}
