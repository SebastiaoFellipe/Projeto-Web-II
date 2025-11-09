package com.bti.projetoweb2.animais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bti.projetoweb2.animais.entities.Reabilitacao;
import com.bti.projetoweb2.animais.services.ReabilitacaoService;

@RestController
@RequestMapping("/api/reabilitacoes")
public class ReabilitacaoController {
    @Autowired
    private final ReabilitacaoService reabilitacaoService;

    public ReabilitacaoController(ReabilitacaoService reabilitacaoService) {
        this.reabilitacaoService = reabilitacaoService;
    }

    @GetMapping//http://localhost:8080/api/reabilitacoes
    public List<Reabilitacao> listarTodos() {
        return reabilitacaoService.listarTodos();
    }
}
