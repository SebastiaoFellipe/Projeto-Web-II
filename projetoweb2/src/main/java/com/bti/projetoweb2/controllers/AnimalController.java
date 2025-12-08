package com.bti.projetoweb2.controllers;

import java.util.List;

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

import com.bti.projetoweb2.entities.Animal;
import com.bti.projetoweb2.services.AnimalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    @Autowired
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    @Operation(
        summary = "Lista todos os animais",
        description = "Retorna uma lista com todos os animais cadastrados"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nenhum animal encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Animal> listarTodos(@RequestParam(required = false) String nome, 
                                    @PageableDefault(size = 5) Pageable pageable) {
        return animalService.listarTodos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Obtém um animal por ID",
        description = "Retorna os detalhes de um animal específico com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animal encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Animal não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Animal buscarPorId(@PathVariable Long id) {
        return animalService.buscarPorId(id);
    }

    @GetMapping("/especie/{especie}")
    @Operation(
        summary = "Busca animais por espécie",
        description = "Retorna uma lista de animais que pertencem à espécie especificada"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animais encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Espécie não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<Animal> buscarPorEspecie(@PathVariable String especie) {
        return animalService.buscarPorEspecie(especie);
    }

    @GetMapping("/status-saude/{statusSaude}")
    @Operation(
        summary = "Busca animais por status de saúde",
        description = "Retorna uma lista de animais com o status de saúde especificado"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animais encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Status Saude não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<Animal> buscarPorStatusSaude(@PathVariable String statusSaude) {
        return animalService.buscarPorStatusSaude(statusSaude);
    }

    @GetMapping("/classificacao/{classificacao}")
    @Operation(
        summary = "Busca animais por classificação",
        description = "Retorna uma lista de animais que pertencem à classificação especificada"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animais encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Classificação nao encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<Animal> buscarPorClassificacao(@PathVariable Animal.Classificacao classificacao) {
        return animalService.buscarPorClassificacao(classificacao);
    }

    @GetMapping("/habitat/{habitatId}")
    @Operation(
        summary = "Busca animais por habitat",
        description = "Retorna uma lista de animais que vivem no habitat especificado"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animais encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "HabitatId não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<Animal> buscarPorHabitatId(@PathVariable Long habitatId) {
        return animalService.buscarPorHabitatId(habitatId);
    }

    @GetMapping("/familia/{familia}")
    @Operation(
        summary = "Busca animais por família",
        description = "Retorna uma lista de animais que pertencem à família especificada"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animais encontrados com sucesso"),
        @ApiResponse(responseCode = "404", description = "Familia não encontrada"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public List<Animal> buscarPorFamilia(@PathVariable String familia) {
        return animalService.buscarPorFamilia(familia);
    }

    @PostMapping
    @Operation(
        summary = "Cria um novo animal",
        description = "Cria um novo animal com base nos dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Animal criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Animal salvar(@RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Atualiza um animal existente",
        description = "Atualiza um animal existente com base nos dados fornecidos"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animal atualizado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "404", description = "Animal não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        return animalService.atualizar(id, animalAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Deleta um animal",
        description = "Deleta um animal com base no ID fornecido"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Animal deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Animal não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public void deletar(@PathVariable Long id) {
        animalService.deletar(id);
    }
}
