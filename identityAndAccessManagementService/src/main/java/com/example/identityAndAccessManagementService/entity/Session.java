package com.example.identityAndAccessManagementService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="sessions")
@AllArgsConstructor
@NoArgsConstructor
public class Session {
    @Column(name="session_id")
    @Id
    private String sessionToken;
    @Column(name="customer_id")
    private Long customerId;
}
