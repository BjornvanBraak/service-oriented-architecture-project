package com.example.balanceService.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int balance;

    //External IDS
    @Column(name="customer_id")
    private int customerId;
}
