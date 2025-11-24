package com.bti.projetoweb2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.entities.Reabilitacao;

@Repository
public interface ReabilitacaoRepository  extends JpaRepository<Reabilitacao, Long> {
    
}
