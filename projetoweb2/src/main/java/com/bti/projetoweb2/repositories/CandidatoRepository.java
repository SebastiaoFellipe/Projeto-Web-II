package com.bti.projetoweb2.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.entities.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    boolean existsByCpf(String cpf);
    Page<Candidato> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
