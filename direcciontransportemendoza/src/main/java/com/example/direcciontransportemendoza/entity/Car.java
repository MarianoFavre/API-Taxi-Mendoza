package com.example.direcciontransportemendoza.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patent;

    @OneToOne(mappedBy = "car")
    private Aditamento aditamento;

}
