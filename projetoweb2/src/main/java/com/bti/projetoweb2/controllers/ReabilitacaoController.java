package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bti.projetoweb2.entities.Reabilitacao;
import com.bti.projetoweb2.services.ReabilitacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/reabilitacoes")
public class ReabilitacaoController {
    @Autowired
    private final ReabilitacaoService reabilitacaoService;

    public ReabilitacaoController(ReabilitacaoService reabilitacaoService) {
        this.reabilitacaoService = reabilitacaoService;
    }

    @GetMapping
    @Operation(
        summary = "Lista todas as reabilitações",
        description = "Retorna uma lista com todas as reabilitações cadastradas"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhuma reabilitação encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Reabilitacao> listarTodos() {
        Pageable pageable = PageRequest.of(0, 5);
        return reabilitacaoService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtém uma reabilitação por ID",
        description = "Retorna os detalhes de uma reabilitação específica com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reabilitação encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Reabilitação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Reabilitacao buscarPorId(@PathVariable Long id) {
        return reabilitacaoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(
        summary = "Salva uma nova reabilitação",
        description = "Adiciona uma nova reabilitação ao sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Reabilitação salva com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Reabilitacao salvar(@RequestBody Reabilitacao reabilitacao) {
        return reabilitacaoService.salvar(reabilitacao);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualiza uma reabilitação existente",
        description = "Atualiza uma reabilitação existente com base nos dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reabilitação atualizada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Reabilitação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Reabilitacao atualizar(@PathVariable Long id, @RequestBody Reabilitacao reabilitacaoAtualizado) {
        return reabilitacaoService.atualizar(id, reabilitacaoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deleta uma reabilitação",
        description = "Deleta uma reabilitação com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Reabilitação deletada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Reabilitação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public void deletar(@PathVariable Long id) {
        reabilitacaoService.deletar(id);
    }
}
