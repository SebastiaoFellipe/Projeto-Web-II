package com.bti.projetoweb2.visitas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.visitas.entities.Visita;
import com.bti.projetoweb2.visitas.servicies.VisitaService;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
@CrossOrigin(origins = "http://localhost:5173")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping
    public Page<Visita> listar(Pageable pageable) {
        return visitaService.listarVisitas(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visita> buscarPorId(@PathVariable int id) {
        Visita visita = visitaService.buscarPorId(id);
        return visita != null ? ResponseEntity.ok(visita) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Visita salvar(@RequestBody Visita visita,
                         @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds) {
        return visitaService.salvarVisita(visita, funcionarioIds);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visita> atualizar(@PathVariable int id, @RequestBody Visita visita,
                                     @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds) {
        visita.setId(id);
        Visita atualizada = visitaService.salvarVisita(visita, funcionarioIds);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        visitaService.deletarVisita(id);
        return ResponseEntity.noContent().build();
    }
}