package com.bti.projetoweb2.funcionarios.controllers;

import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    public ResponseEntity<?> salvarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            Funcionario salvo = funcionarioService.salvar(funcionario);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            Funcionario atualizado = funcionarioService.atualizar(id, funcionario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}