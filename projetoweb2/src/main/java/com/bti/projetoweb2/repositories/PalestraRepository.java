package com.bti.projetoweb2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.entities.Palestra;

public interface PalestraRepository extends JpaRepository<Palestra, Integer> {
    Page<Palestra> findByTemaContainingIgnoreCase(String tema, Pageable pageable);
}
