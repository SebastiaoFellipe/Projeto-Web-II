package com.bti.projetoweb2.animais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.animais.entities.Reabilitacao;

@Repository
public interface ReabilitacaoRepository  extends JpaRepository<Reabilitacao, Long> {
    
}
