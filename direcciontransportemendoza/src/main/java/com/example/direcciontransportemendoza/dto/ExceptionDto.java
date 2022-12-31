package com.example.direcciontransportemendoza.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ExceptionDto {
    private int statusCode;
    private String bugMessage;
}
