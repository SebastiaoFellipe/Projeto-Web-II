package com.bti.projetoweb2.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bti.projetoweb2.entities.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {
    Page<Visita> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
