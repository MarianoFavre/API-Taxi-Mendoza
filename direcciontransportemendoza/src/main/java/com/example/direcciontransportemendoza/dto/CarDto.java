package com.example.direcciontransportemendoza.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CarDto {

    //@NotEmpty(message = "patent attribute missing.")
    //@NotBlank(message = "Enter a patent.")
    private String patent;
}
