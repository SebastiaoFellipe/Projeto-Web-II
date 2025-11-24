package com.bti.projetoweb2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.entities.Candidato;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
    boolean existsByCpf(String cpf);
}
