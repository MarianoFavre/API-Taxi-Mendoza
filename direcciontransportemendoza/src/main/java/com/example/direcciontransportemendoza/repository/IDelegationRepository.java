package com.example.direcciontransportemendoza.repository;

import com.example.direcciontransportemendoza.entity.Delegation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IDelegationRepository extends JpaRepository<Delegation, Long> {

    List<Delegation> findByName(String name);
}
