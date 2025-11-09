package com.bti.projetoweb2.candidato.controllers;

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

import com.bti.projetoweb2.candidato.entities.Candidato;
import com.bti.projetoweb2.candidato.services.CandidatoService;

@RestController
@RequestMapping("api/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping //http://localhost:8080/api/candidatos
    public List<Candidato> listarTodos() {
        return candidatoService.listarTodos();
    }

    @GetMapping("/{id}") //http://localhost:8080/api/candidatos/1
    public Candidato buscarPorId(@PathVariable Long id) {
        return candidatoService.buscarPorId(id);
    }

    @PostMapping //http://localhost:8080/api/candidatos
    public Candidato salvar(@RequestBody Candidato candidato) {
        return candidatoService.salvar(candidato);
    }

    @PutMapping("/{id}") //http://localhost:8080/api/candidatos/1
    public Candidato atualizar(@PathVariable Long id, @RequestBody Candidato candidatoAtualizado) {
        return candidatoService.atualizar(id, candidatoAtualizado);
    }

    @DeleteMapping("/{id}") //http://localhost:8080/api/candidatos/1
    public void deletar(@PathVariable Long id) {
        candidatoService.deletar(id);
    }
}
