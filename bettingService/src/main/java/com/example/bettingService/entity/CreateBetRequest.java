package com.example.bettingService.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBetRequest {
    private float betValueCreator;
    private float betValueTaker;
    private boolean betOnHomeTeamCreator;
    //EXTERNAL IDS
    private Long betCreatorId;
    private Long gameId;
}

