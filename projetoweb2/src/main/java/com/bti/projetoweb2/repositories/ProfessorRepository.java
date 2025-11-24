package com.bti.projetoweb2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.entities.Professor;
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}