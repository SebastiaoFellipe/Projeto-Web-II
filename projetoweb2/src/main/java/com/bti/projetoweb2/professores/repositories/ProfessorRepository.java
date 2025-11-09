package com.bti.projetoweb2.professores.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.professores.entities.Professor;
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}