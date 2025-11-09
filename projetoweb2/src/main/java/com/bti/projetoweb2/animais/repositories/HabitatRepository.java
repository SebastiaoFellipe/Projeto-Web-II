package com.bti.projetoweb2.animais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.animais.entities.Habitat;

public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    Habitat findByDescricao(String descricao);
}
