package com.example.direcciontransportemendoza.exception;


import com.example.direcciontransportemendoza.dto.ExceptionDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException exception) {
        ExceptionDto exceptionDto = new ExceptionDto(404, exception.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> illegalArgumentException(IllegalArgumentException exception) {
        ExceptionDto exceptionDto = new ExceptionDto(500, exception.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<?> emptyResultDataAccessException(InternalServerErrorException exception) {
        ExceptionDto exceptionDto = new ExceptionDto(500, exception.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    //METODO PARA CAPTURAR UN SOLO ERROR POR VALIDACION
    //public ResponseEntity<?> validacionErronea(MethodArgumentNotValidException exception){
    //    ExceptionDto exceptionDto = new ExceptionDto(400, exception.getFieldError().getDefaultMessage());
    //    return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    //}

    //METODO PARA CAPTURAR VARIOS ERRORES POR VALIDACION
    public ResponseEntity<?> validacionErronea1(MethodArgumentNotValidException exception) {

        List<String> listaMensajeError = exception.getAllErrors().stream()
                .map(error -> error.getDefaultMessage()).collect(Collectors.toList());

        List<ExceptionDto> exceptionDto = new ArrayList<>();
        listaMensajeError.forEach(mensajeError -> exceptionDto.add(new ExceptionDto(400, mensajeError)));

        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
