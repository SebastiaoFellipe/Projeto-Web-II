package com.bti.projetoweb2.professores.controllers;

import com.bti.projetoweb2.professores.entities.Professor;
import com.bti.projetoweb2.professores.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    public List<Professor> listProfessores() {
        return professorService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        Professor professor = professorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<?> saveProfessor(@RequestBody Professor professor) {
        try {
            Professor salvo = professorService.salvar(professor);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor atualizado = professorService.atualizar(id, professor);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        try {
            professorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}