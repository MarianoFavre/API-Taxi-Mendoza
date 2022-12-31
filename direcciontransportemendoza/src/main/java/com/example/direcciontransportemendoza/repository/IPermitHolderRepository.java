package com.example.direcciontransportemendoza.repository;


import com.example.direcciontransportemendoza.entity.PermitHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPermitHolderRepository extends JpaRepository<PermitHolder, Long> {
    PermitHolder findByNameAndLastName(String name, String lastName);
    Optional<PermitHolder> findById(Long id);

}
