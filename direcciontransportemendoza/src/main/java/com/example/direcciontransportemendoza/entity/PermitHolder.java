package com.example.direcciontransportemendoza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@Entity
@Table(name = "permitholders")
public class PermitHolder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastName;
    private String cuit;

    //Nombre del atributo creado en la otra entidad y no el nombre de esta entidad.
    @OneToMany(mappedBy = "permitHolder", cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private List<Aditamento> aditamentos;
}
