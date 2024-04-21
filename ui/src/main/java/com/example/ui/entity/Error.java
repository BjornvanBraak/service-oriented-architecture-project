package com.example.ui.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Error {
    public String userErrorMsg;
    public String devErrorMsg;
}
