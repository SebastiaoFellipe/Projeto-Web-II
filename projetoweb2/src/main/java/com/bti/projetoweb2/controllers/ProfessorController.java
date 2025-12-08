package com.bti.projetoweb2.controllers;

import com.bti.projetoweb2.entities.Professor;
import com.bti.projetoweb2.services.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/professores")
@CrossOrigin(origins = "http://localhost:5173")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping
    @Operation(summary = "Lista todos os professores", description = "Retorna uma lista paginada de professores cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Professor> listProfessores(@RequestParam(required = false) String nome, 
                                            @PageableDefault(size = 5) Pageable pageable) {
        return professorService.listarTodos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um professor por ID", description = "Retorna os detalhes de um professor específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Professor> buscarPorId(@PathVariable Long id) {
        Professor professor = professorService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Professor não encontrado"));
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    @Operation(summary = "Cria um novo professor", description = "Cadastra um novo professor no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> saveProfessor(@RequestBody Professor professor) {
        try {
            Professor salvo = professorService.salvar(professor);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um professor existente", description = "Atualiza os dados de um professor existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Professor> atualizarProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        Professor atualizado = professorService.atualizar(id, professor);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um professor", description = "Remove um professor do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Professor deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Professor não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        try {
            professorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}