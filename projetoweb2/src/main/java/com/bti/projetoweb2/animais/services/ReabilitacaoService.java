package com.bti.projetoweb2.animais.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bti.projetoweb2.animais.entities.Reabilitacao;
import com.bti.projetoweb2.animais.repositories.ReabilitacaoRepository;

@Service
public class ReabilitacaoService {
    private final ReabilitacaoRepository reabilitacaoRepository;

    public ReabilitacaoService(ReabilitacaoRepository reabilitacaoRepository) {
        this.reabilitacaoRepository = reabilitacaoRepository;
    }

    public List<Reabilitacao> listarTodos() {
        return reabilitacaoRepository.findAll();
    }
}
