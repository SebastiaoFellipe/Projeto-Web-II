package com.bti.projetoweb2.animais.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.animais.entities.Reabilitacao;

public interface ReabilitacaoRepository  extends JpaRepository<Reabilitacao, Long> {
    
}
