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

import com.bti.projetoweb2.entities.Habitat;
import com.bti.projetoweb2.services.HabitatService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {
    @Autowired
    private final HabitatService habitatService;

    public HabitatController(HabitatService habitatService) {
        this.habitatService = habitatService;
    }

    @GetMapping
    @Operation(
        summary = "Listar todos os habitats",
        description = "Retorna uma lista paginada de todos os habitats cadastrados no sistema."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de habitats retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Page<Habitat> listarTodos(@RequestParam(required = false) String descricao, 
                                    @PageableDefault(size = 5) Pageable pageable) {
        return habitatService.listarTodos(descricao, pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obter habitat por ID",
        description = "Retorna os detalhes de um habitat específico com base no ID fornecido."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Habitat encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Habitat não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Habitat buscarPorId(@PathVariable Long id) {
        return habitatService.buscarPorId(id);
    }

    @PostMapping
    @Operation(
        summary = "Criar um novo habitat",
        description = "Adiciona um novo habitat ao sistema com os detalhes fornecidos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Habitat criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Requisição inválida"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Habitat salvar(@RequestBody Habitat habitat) {
        return habitatService.salvar(habitat);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualizar um habitat existente",
        description = "Atualiza um habitat existente com base nos dados fornecidos."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Habitat atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Habitat não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public Habitat atualizar(@PathVariable Long id, @RequestBody Habitat habitatAtualizado) {
        return habitatService.atualizar(id, habitatAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deletar um habitat",
        description = "Remove um habitat do sistema com base no ID fornecido."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Habitat deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Habitat não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public void deletar(@PathVariable Long id) {
        habitatService.deletar(id);
    }
}
