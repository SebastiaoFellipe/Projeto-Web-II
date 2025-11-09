package com.bti.projetoweb2.candidato.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.candidato.entities.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    boolean existsByCpf(String cpf);
}
