package com.example.ui.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBetAttempt {
    private float betValueCreator = 5;
    private float betValueTaker = 5;
    private boolean betOnHomeTeamCreator;
    //EXTERNAL IDS
    private Long betCreatorId;
    private Long gameId;
}
