package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
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
}
