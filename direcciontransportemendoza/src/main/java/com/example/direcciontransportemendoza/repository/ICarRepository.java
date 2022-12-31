package com.example.direcciontransportemendoza.repository;

import com.example.direcciontransportemendoza.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICarRepository extends JpaRepository<Car, Long> {
}
