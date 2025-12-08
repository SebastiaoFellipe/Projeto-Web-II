package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bti.projetoweb2.entities.Candidato;
import com.bti.projetoweb2.services.CandidatoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("api/candidatos")
public class CandidatoController {
    @Autowired
    private CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping
    @Operation(
        summary = "Lista todos os candidatos",
        description = "Retorna uma lista com todos os candidatos cadastrados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum candidato encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Candidato> listarTodos(@RequestParam(required = false) String nome, 
                                    @PageableDefault(size = 5) Pageable pageable) {
        return candidatoService.listarTodos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtém um candidato por ID",
        description = "Retorna os detalhes de um candidato específico com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Candidato encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Candidato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Candidato buscarPorId(@PathVariable Long id) {
        return candidatoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(
        summary = "Salva um novo candidato",
        description = "Adiciona um novo candidato ao sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Candidato salvo com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Candidato salvar(@RequestBody Candidato candidato) {
        return candidatoService.salvar(candidato);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualiza um candidato existente",
        description = "Atualiza um candidato existente com base nos dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Candidato atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Candidato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Candidato atualizar(@PathVariable Long id, @RequestBody Candidato candidatoAtualizado) {
        return candidatoService.atualizar(id, candidatoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deleta um candidato",
        description = "Deleta um candidato com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Candidato deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Candidato não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public void deletar(@PathVariable Long id) {
        candidatoService.deletar(id);
    }
}
