package com.bti.projetoweb2.professor.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bti.projetoweb2.professor.entities.Professor;
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}