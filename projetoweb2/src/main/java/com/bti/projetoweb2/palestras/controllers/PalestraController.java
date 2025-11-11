package com.bti.projetoweb2.palestras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.palestras.entities.Palestra;
import com.bti.projetoweb2.palestras.servicies.PalestraService;

@RestController
@RequestMapping("/api/palestras")
@CrossOrigin(origins = "http://localhost:5173")
public class PalestraController {

    @Autowired
    private PalestraService palestraService;

    @GetMapping
    public Page<Palestra> listarPalestras(Pageable pageable) {
        return palestraService.listarPalestra(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Palestra> buscarPalestraPorId(@PathVariable Integer id) {
        Palestra palestra = palestraService.buscarPorId(id);
        return palestra != null ? ResponseEntity.ok(palestra) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Palestra salvarPalestra(@RequestBody Palestra palestra) {
        return palestraService.salvarPalestra(palestra);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Palestra> atualizarPalestra(@PathVariable Integer id, @RequestBody Palestra palestra) {
        palestra.setId(id);
        Palestra atualizada = palestraService.salvarPalestra(palestra);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPalestra(@PathVariable Integer id) {
        palestraService.deletarPalestra(id);
        return ResponseEntity.noContent().build();
    }
}