package com.bti.projetoweb2.controllers;

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

import com.bti.projetoweb2.entities.Candidato;
import com.bti.projetoweb2.services.CandidatoService;

@RestController
@RequestMapping("api/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping
    public List<Candidato> listarTodos() {
        return candidatoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Candidato buscarPorId(@PathVariable Long id) {
        return candidatoService.buscarPorId(id);
    }

    @PostMapping
    public Candidato salvar(@RequestBody Candidato candidato) {
        return candidatoService.salvar(candidato);
    }

    @PutMapping("/{id}")
    public Candidato atualizar(@PathVariable Long id, @RequestBody Candidato candidatoAtualizado) {
        return candidatoService.atualizar(id, candidatoAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        candidatoService.deletar(id);
    }
}
