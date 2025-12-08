package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.entities.Palestra;
import com.bti.projetoweb2.services.PalestraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/palestras")
@CrossOrigin(origins = "http://localhost:5173")
public class PalestraController {

    @Autowired
    private PalestraService palestraService;

    @GetMapping
    @Operation(summary = "Lista todas as palestras", description = "Retorna uma lista paginada de palestras cadastradas")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Palestra> listarPalestras(@RequestParam(required = false) String tema, 
                                        @PageableDefault(size = 5) Pageable pageable) {
        return palestraService.listarTodos(tema, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém uma palestra por ID", description = "Retorna os detalhes de uma palestra específica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Palestra encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Palestra não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Palestra> buscarPalestraPorId(@PathVariable Integer id) {
        Palestra palestra = palestraService.buscarPorId(id);
        return palestra != null ? ResponseEntity.ok(palestra) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @Operation(summary = "Cria uma nova palestra", description = "Cadastra uma nova palestra no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Palestra criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Palestra salvarPalestra(@RequestBody Palestra palestra) {
        return palestraService.salvarPalestra(palestra);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza uma palestra existente", description = "Atualiza os dados de uma palestra existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Palestra atualizada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Palestra não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Palestra> atualizarPalestra(@PathVariable Integer id, @RequestBody Palestra palestra) {
        palestra.setId(id);
        Palestra atualizada = palestraService.salvarPalestra(palestra);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma palestra", description = "Remove uma palestra do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Palestra deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Palestra não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deletarPalestra(@PathVariable Integer id) {
        palestraService.deletarPalestra(id);
        return ResponseEntity.noContent().build();
    }
}