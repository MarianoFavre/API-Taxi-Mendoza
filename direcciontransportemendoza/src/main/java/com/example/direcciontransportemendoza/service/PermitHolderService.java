package com.example.direcciontransportemendoza.service;


import com.example.direcciontransportemendoza.dto.*;
import com.example.direcciontransportemendoza.entity.Aditamento;
import com.example.direcciontransportemendoza.entity.PermitHolder;
import com.example.direcciontransportemendoza.exception.InternalServerErrorException;
import com.example.direcciontransportemendoza.exception.NotFoundException;
import com.example.direcciontransportemendoza.repository.IAditamentoRepository;
import com.example.direcciontransportemendoza.repository.IPermitHolderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PermitHolderService implements IPermitHolderService {

    private IPermitHolderRepository permitHolderRepository;

    public PermitHolderService(IPermitHolderRepository permitHolderRepository) {
        this.permitHolderRepository = permitHolderRepository;
    }

    //No inyecto por constructor porque no me corre la aplicacion.
    @Autowired
    private IAditamentoRepository aditamentoRepository;

    @Override
    public List<PermitHolderDto> obtenerPermisionarios() {
        ModelMapper modelmapper = new ModelMapper();

        List<PermitHolder> permitHolders = permitHolderRepository.findAll();

        if (permitHolders.isEmpty())
            throw new NotFoundException("No hay permisionarios de taxi registrados, por favor carguelos.");

        List<PermitHolderDto> permitHoldersDto = new ArrayList<>();

        permitHolders.stream().forEach(permitHolder -> permitHoldersDto
                .add(modelmapper.map(permitHolder, PermitHolderDto.class)));

        return permitHoldersDto;
    }

    @Override
    public PermitHolderDto obtenerPermisionarioPorId(Long id) {
        ModelMapper modelmapper = new ModelMapper();

        Optional<PermitHolder> permitHolder = permitHolderRepository.findById(id);
        //PermitHolder permitHolder = permitHolderRepository.findById(id).orElse(null);

        if (!permitHolder.isPresent())
            throw new NotFoundException("No está registrado el permisionario de taxi de id="
                    + id + ", por favor carguelo o busque con otro id.");

        PermitHolderDto permitHolderDto = modelmapper.map(permitHolder, PermitHolderDto.class);
        return permitHolderDto;
    }

    @Override
    public PermitHolderDto obtenerPermisionarioPorNombreYApellido(String name, String lastName) {
        ModelMapper modelmapper = new ModelMapper();

        PermitHolder permitHolder = permitHolderRepository.findByNameAndLastName(name, lastName);

        PermitHolderDto permitHolderDto = modelmapper.map(permitHolder, PermitHolderDto.class);
        return permitHolderDto;
    }

    @Override
    public RespCarPatentDto obtenerPatenteDeAutoDePermisionarioPorId(Long id) {
        ModelMapper modelmapper = new ModelMapper();

        Optional<PermitHolder> permitHolder = permitHolderRepository.findById(id);
        //PermitHolder permitHolder = permitHolderRepository.findById(id).orElse(null);

        if (!permitHolder.isPresent())
            throw new NotFoundException("No está registrado el permisionario de taxi de id=" + id + " .");

        PermitHolderDto permitHolderDto = modelmapper.map(permitHolder, PermitHolderDto.class);

        RespCarPatentDto patentDto = new RespCarPatentDto();

        patentDto.setPatent(permitHolderDto.getAditamentos().stream()
                .map(aditamentoDto -> aditamentoDto.getCar().getPatent())
                .collect(Collectors.toList()));

        return patentDto;
    }

    @Override
    public RespPermitHolderDto guardarPermisionario(PermitHolderDto permitHolderDto) {
        //Si existe el permisionario lanzar una excepción.
        List<PermitHolder> permitHolders = permitHolderRepository.findAll();
        if ((permitHolders.stream().filter(p -> p.getCuit().equals(permitHolderDto.getCuit())).findFirst()).isPresent())
            throw new InternalServerErrorException("El permisionario de taxi de cuit "
                    + permitHolderDto.getCuit() + " ya está registrado.");

        ModelMapper modelMapper = new ModelMapper();
        PermitHolder permitHolder = modelMapper.map(permitHolderDto, PermitHolder.class);

        permitHolder.getAditamentos().forEach(aditamento -> aditamento.setPermitHolder(permitHolder));
        permitHolder.getAditamentos().forEach(aditamento -> aditamento.getCar().setAditamento(aditamento));
        permitHolder.getAditamentos().forEach(aditamento -> aditamento.getDelegation().setAditamento(aditamento));

        PermitHolder persistPermitHolder = permitHolderRepository.save(permitHolder);

        RespPermitHolderDto respPermitHolderDto = new RespPermitHolderDto();
        respPermitHolderDto.setPermitHolderDto(modelMapper.map(persistPermitHolder, PermitHolderDto.class));
        respPermitHolderDto.setMessage("El permisionario se guardó con éxito...");
        return respPermitHolderDto;
    }

    @Override
    public RespPermitHolderDto modificarPermisionarioPorId(Long id, PermitHolderDto permitHolderDto) {

        if (!permitHolderRepository.existsById(id))
            throw new InternalServerErrorException("No se puede actualizar el permisionario de taxi de id="
                    + id + " ya que no está registrado.");

        //Optional<PermitHolder> permitHolder = permitHolderRepository.findById(id);
        PermitHolder permitHolder = permitHolderRepository.findById(id).orElse(null);

        ModelMapper modelMapper = new ModelMapper();
        PermitHolder editPermitHolder = modelMapper.map(permitHolderDto, PermitHolder.class);

        editPermitHolder.getAditamentos().forEach(aditamento -> aditamento.setPermitHolder(permitHolder));
        editPermitHolder.getAditamentos().forEach(aditamento -> aditamento.getCar().setAditamento(aditamento));
        editPermitHolder.getAditamentos().forEach(aditamento -> aditamento.getDelegation().setAditamento(aditamento));

        permitHolder.setName(editPermitHolder.getName());
        permitHolder.setLastName(editPermitHolder.getLastName());
        permitHolder.setCuit(editPermitHolder.getCuit());

        List<Aditamento> aditamentosPermitHolder = permitHolder.getAditamentos();
        List<Aditamento> aditamentosEditPermitHolder = editPermitHolder.getAditamentos();

        //Funciona si aditamentosPermitHolder(viejo) <= aditamentosEditPermitHolder(nuevo), pero si es menor solo
        // registra la misma cantidad que aditamentosPermitHolder por lo que debería guardar los aditamentos que faltan
        // con aditamentoRepository.
        //Para aditamentosPermitHolder(viejo) > aditamentosEditPermitHolder(nuevo) debería eliminar la cantidad de
        // aditamentos en exceso con aditamentoRepository.
        for (int i = 0; i < aditamentosPermitHolder.size(); i++) {

            aditamentosPermitHolder.get(i)
                    .setNumber(aditamentosEditPermitHolder.get(i).getNumber());

            aditamentosPermitHolder.get(i).getDelegation()
                    .setName(aditamentosEditPermitHolder.get(i).getDelegation().getName());

            aditamentosPermitHolder.get(i).getCar()
                    .setPatent(aditamentosEditPermitHolder.get(i).getCar().getPatent());
        }

        PermitHolder persistPermitHolder = permitHolderRepository.save(permitHolder);

        RespPermitHolderDto respPermitHolderDto = new RespPermitHolderDto();
        respPermitHolderDto.setPermitHolderDto(modelMapper.map(persistPermitHolder, PermitHolderDto.class));
        respPermitHolderDto.setMessage("El permisionario se modificó con éxito...");
        return respPermitHolderDto;
    }

    @Override
    public RespPermitHolderDto eliminarPermisionarioPorId(Long id) {

        if (!permitHolderRepository.existsById(id))
            throw new InternalServerErrorException("No se puede borrar el permisionario de taxi de id="
                    + id + " ya que no está registrado.");

        permitHolderRepository.deleteById(id);

        RespPermitHolderDto respPermitHolderDto = new RespPermitHolderDto();
        respPermitHolderDto.setMessage("El permisionario de id=" + id + " se borró con éxito...");
        return respPermitHolderDto;
    }

    @Override
    public RespNumberTaxiDelegation cantidadTaxiPorDelegacion(String name) {

        //List<PermitHolder> permitHolders = permitHolderRepository.findAll();
//
        //int numberTaxiDelegation = 0;
//
        //String delegation = name;
//
        //for (int i = 0; i < permitHolders.size(); i++) {
        //    List<Boolean> numberAditamento = permitHolders.get(i).getAditamentos().stream()
        //            .map(aditamento -> delegation.equals(aditamento.getDelegation().getName()))
        //            .collect(Collectors.toList());
//
        //    numberTaxiDelegation += numberAditamento.size();
        //}
//
        //RespNumberTaxiDelegation respNumberTaxiDelegation = new RespNumberTaxiDelegation();
        //respNumberTaxiDelegation.setNumberTaxi(numberTaxiDelegation);
//
        //return respNumberTaxiDelegation;

        //List<Aditamento> aditamento = aditamentoRepository.findAll();
//
        //int numberTaxiDelegation = 0;
        //for (int i = 0; i < aditamento.size(); i++) {
//
        //    if (aditamento.get(i).getDelegation().getName().equals(name)) {
        //        numberTaxiDelegation += 1;
        //    }
        //}
//
        //RespNumberTaxiDelegation respNumberTaxiDelegation = new RespNumberTaxiDelegation();
        //respNumberTaxiDelegation.setNumberTaxi(numberTaxiDelegation);
//
        //return respNumberTaxiDelegation;
        return null;

    }
}
