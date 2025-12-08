package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.entities.Visita;
import com.bti.projetoweb2.services.VisitaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequestMapping("/api/visitas")
@CrossOrigin(origins = "http://localhost:5173")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @GetMapping
    @Operation(summary = "Lista todas as visitas", description = "Retorna uma lista paginada de visitas cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Visita> listar(
            @RequestParam(required = false) String nome,
            @PageableDefault(size = 5) Pageable pageable) {
        return visitaService.listarTodos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma visita por ID", description = "Retorna os detalhes de uma visita específica com base no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Visita encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Visita> buscarPorId(@PathVariable int id) {
        Visita visita = visitaService.buscarPorId(id);
        return visita != null ? ResponseEntity.ok(visita) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Cria uma nova visita", description = "Cadastra uma nova visita no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Visita criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Visita salvar(@RequestBody Visita visita,
                         @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds) {
        return visitaService.salvarVisita(visita, funcionarioIds);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma visita existente", description = "Atualiza os dados de uma visita existente com base no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Visita atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Visita> atualizar(@PathVariable int id, @RequestBody Visita visita,
                                     @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds) {
        visita.setId(id);
        Visita atualizada = visitaService.salvarVisita(visita, funcionarioIds);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma visita", description = "Remove uma visita do sistema com base no ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Visita deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Visita não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        visitaService.deletarVisita(id);
        return ResponseEntity.noContent().build();
    }
}