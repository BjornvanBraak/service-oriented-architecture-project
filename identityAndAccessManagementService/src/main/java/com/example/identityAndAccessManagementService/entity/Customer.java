package com.example.identityAndAccessManagementService.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JsonIgnore
    private String password;
    private String email;
    private Boolean cruks;

    //EXTERNAL IDS
//    private Long[] betsId;

    // Getters and setters generated with lombok
}