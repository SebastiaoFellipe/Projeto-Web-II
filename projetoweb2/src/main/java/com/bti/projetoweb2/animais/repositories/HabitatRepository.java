package com.bti.projetoweb2.animais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.animais.entities.Habitat;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    Habitat findByDescricao(String descricao);
}
