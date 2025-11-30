package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bti.projetoweb2.entities.Alimentacao;
import com.bti.projetoweb2.services.AlimentacaoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/alimentacao")
public class AlimentacaoController {
    @Autowired
    private final AlimentacaoService alimentacaoService;

    public AlimentacaoController(AlimentacaoService alimentacaoService) {
        this.alimentacaoService = alimentacaoService;
    }

    @GetMapping
    @Operation(
        summary = "Lista todas as alimentações de animais",
        description = "Retorna uma lista com todas as alimentações cadastradas no sistema"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhuma alimentação encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Alimentacao> listarTodos() {
        Pageable pageable = PageRequest.of(0, 5);
        return alimentacaoService.listarTodos(pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtém uma alimentação por ID",
        description = "Retorna os detalhes de uma alimentação específica com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Alimentação encontrada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Alimentação não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Alimentacao buscarPorId(@PathVariable Long id) {
        return alimentacaoService.buscarPorId(id);
    }

    @PostMapping
    @Operation(
        summary = "Cria um nova alimentação",
        description = "Cria um nova alimentação com base nos dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Alimentação adicionada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Alimentacao salvar(@RequestBody Alimentacao alimentacao) {
        return alimentacaoService.cadastrar(alimentacao);
    }
}
