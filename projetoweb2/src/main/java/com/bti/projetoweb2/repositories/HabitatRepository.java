package com.bti.projetoweb2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.entities.Habitat;

@Repository
public interface HabitatRepository extends JpaRepository<Habitat, Long> {
    Habitat findByDescricao(String descricao);
    Page<Habitat> findByDescricaoContainingIgnoreCase(String descricao, Pageable pageable);
}
