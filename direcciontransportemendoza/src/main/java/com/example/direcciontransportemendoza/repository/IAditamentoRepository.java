package com.example.direcciontransportemendoza.repository;

import com.example.direcciontransportemendoza.entity.Aditamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IAditamentoRepository extends JpaRepository<Aditamento, Long> {
    List<Aditamento> findByDelegation(int name);
}
