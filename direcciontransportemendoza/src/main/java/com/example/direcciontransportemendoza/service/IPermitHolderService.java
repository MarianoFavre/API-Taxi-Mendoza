package com.example.direcciontransportemendoza.service;

import com.example.direcciontransportemendoza.dto.PermitHolderDto;
import com.example.direcciontransportemendoza.dto.RespCarPatentDto;
import com.example.direcciontransportemendoza.dto.RespNumberTaxiDelegation;
import com.example.direcciontransportemendoza.dto.RespPermitHolderDto;

import java.util.List;


public interface IPermitHolderService {
    List<PermitHolderDto> obtenerPermisionarios();

    PermitHolderDto obtenerPermisionarioPorId(Long id);

    PermitHolderDto obtenerPermisionarioPorNombreYApellido(String name, String lastName);

    public RespCarPatentDto obtenerPatenteDeAutoDePermisionarioPorId(Long id);

    RespPermitHolderDto guardarPermisionario(PermitHolderDto permitHolderDto);

    RespPermitHolderDto modificarPermisionarioPorId(Long id, PermitHolderDto permitHolderDto);

    RespPermitHolderDto eliminarPermisionarioPorId(Long id);

    RespNumberTaxiDelegation cantidadTaxiPorDelegacion(String name);



}
