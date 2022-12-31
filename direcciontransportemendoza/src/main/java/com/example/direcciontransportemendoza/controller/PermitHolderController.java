package com.example.direcciontransportemendoza.controller;

import com.example.direcciontransportemendoza.dto.PermitHolderDto;
import com.example.direcciontransportemendoza.service.PermitHolderService;
import com.example.direcciontransportemendoza.service.IPermitHolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/taxi")
public class PermitHolderController {

    private IPermitHolderService permitHolderService;

    public PermitHolderController(PermitHolderService permitHolderService) {
        this.permitHolderService = permitHolderService;
    }

    @GetMapping("/getAllPermitHolder")
    public ResponseEntity<?> obtenerPermisionarios() {
        return new ResponseEntity<>(permitHolderService.obtenerPermisionarios(), HttpStatus.OK);
    }

    @GetMapping("/getPermitHolderById/{id}")
    public ResponseEntity<?> obtenerPermisionarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(permitHolderService.obtenerPermisionarioPorId(id), HttpStatus.OK);
    }

    @GetMapping("/getPermitHolderByNameAndLastName/{name}/{lastName}")
    public ResponseEntity<?> obtenerPermisionarioPorNombreYApellido(@PathVariable String name, @PathVariable String lastName) {
        return new ResponseEntity<>(permitHolderService.obtenerPermisionarioPorNombreYApellido(name, lastName), HttpStatus.OK);
    }

    @GetMapping("/getCarPatentPermitHolderById/{id}")
    public ResponseEntity<?> obtenerPatenteDeAutoDePermisionarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(permitHolderService.obtenerPatenteDeAutoDePermisionarioPorId(id), HttpStatus.OK);
    }

    @GetMapping("/getNumberTaxiByDelegation/{name}")
    public ResponseEntity<?> cantidadTaxiPorDelegacion(@PathVariable String name) {
        return new ResponseEntity<>(permitHolderService.cantidadTaxiPorDelegacion(name), HttpStatus.OK);
    }

    @PostMapping("/savePermitHolder")
    public ResponseEntity<?> guardarPermisionario(@Valid @RequestBody PermitHolderDto permitHolderDto) {
        return new ResponseEntity<>(permitHolderService.guardarPermisionario(permitHolderDto), HttpStatus.OK);
    }

    @PutMapping("/editPermitHolderById/{id}")
    public ResponseEntity<?> modificarPermisionarioPorId(@PathVariable Long id, @Valid @RequestBody PermitHolderDto permitHolderDto) {
        return new ResponseEntity<>(permitHolderService.modificarPermisionarioPorId(id, permitHolderDto), HttpStatus.OK);
    }

    @DeleteMapping("/deletePermitHolderById/{id}")
    public ResponseEntity<?> eliminarPermisionarioPorId(@PathVariable Long id) {
        return new ResponseEntity<>(permitHolderService.eliminarPermisionarioPorId(id), HttpStatus.OK);
    }
}

