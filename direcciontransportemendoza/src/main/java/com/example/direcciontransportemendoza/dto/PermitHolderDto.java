package com.example.direcciontransportemendoza.dto;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

import java.util.List;


@Getter
@Setter
public class PermitHolderDto {

    private Long id;

    @NotEmpty(message = "Enter a name.")
    private String name;

    @NotEmpty(message = "Enter a lastName.")
    private String lastName;

    @NotEmpty(message = "Enter a cuit.")
    @Size(min = 13, max = 13, message = "The cuit must have 13 characters.")
    private String cuit;

    @NotNull(message = "aditamentos attribute missing.")
    private List<AditamentoDto> aditamentos;
}
