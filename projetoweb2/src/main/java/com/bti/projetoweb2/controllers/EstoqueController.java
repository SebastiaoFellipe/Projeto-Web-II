package com.bti.projetoweb2.controllers;

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

import com.bti.projetoweb2.entities.Estoque;
import com.bti.projetoweb2.services.EstoqueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/estoques")
public class EstoqueController {
    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @GetMapping
    @Operation(
        summary = "Lista todos os estoques",
        description = "Retorna uma lista com todos os estoques cadastrados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum estoque encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Estoque> listarTodos(@RequestParam(required = false) String nomeProduto, 
                                    @PageableDefault(size = 5) Pageable pageable) {
        return estoqueService.listarTodos(nomeProduto, pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtém um estoque por ID",
        description = "Retorna os detalhes de um estoque específico com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estoque encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Estoque buscarPorId(@PathVariable Long id) {
        return estoqueService.buscarPorId(id);
    }

    @PostMapping
    @Operation(
        summary = "Cria um novo estoque",
        description = "Cria um novo estoque com base nos dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Estoque criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Estoque salvar(@RequestBody Estoque estoque) {
        return estoqueService.salvar(estoque);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualiza um estoque",
        description = "Atualiza os dados de um estoque existente com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estoque atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Estoque atualizar(@PathVariable Long id, @RequestBody Estoque estoqueAtualizado) {
        return estoqueService.atualizar(id, estoqueAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deleta um estoque",
        description = "Deleta um estoque existente com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Estoque deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Estoque não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public void deletar(@PathVariable Long id) {
        estoqueService.excluir(id);
    }
}
