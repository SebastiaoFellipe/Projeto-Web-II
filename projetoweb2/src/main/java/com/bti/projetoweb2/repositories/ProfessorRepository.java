package com.bti.projetoweb2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bti.projetoweb2.entities.Professor;
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Page<Professor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}