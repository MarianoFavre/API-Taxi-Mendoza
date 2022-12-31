package com.example.direcciontransportemendoza.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AditamentoDto {


    //@NotEmpty(message = "Missing Aditamento number attribute.")
    //@NotBlank(message = "Enter Aditamento number.")
    @NotNull
    private String number;


    private CarDto car;
    private DelegationDto delegation;
}
