package com.example.ui.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerResponse {
    private Long id;
    private String name;
    private String email;
    private Boolean cruks;
}
