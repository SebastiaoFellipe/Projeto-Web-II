package com.bti.projetoweb2.animais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{id}")//http://localhost:8080/api/reabilitacoes/1
    public Reabilitacao buscarPorId(@PathVariable Long id) {
        return reabilitacaoService.buscarPorId(id);
    }

    @PostMapping//http://localhost:8080/api/reabilitacoes
    public Reabilitacao salvar(@RequestBody Reabilitacao reabilitacao) {
        return reabilitacaoService.salvar(reabilitacao);
    }

    @PutMapping("/{id}")//http://localhost:8080/api/reabilitacoes/1
    public Reabilitacao atualizar(@PathVariable Long id, @RequestBody Reabilitacao reabilitacaoAtualizado) {
        return reabilitacaoService.atualizar(id, reabilitacaoAtualizado);
    }

    @DeleteMapping("/{id}")//http://localhost:8080/api/reabilitacoes/1
    public void deletar(@PathVariable Long id) {
        reabilitacaoService.deletar(id);
    }
}
