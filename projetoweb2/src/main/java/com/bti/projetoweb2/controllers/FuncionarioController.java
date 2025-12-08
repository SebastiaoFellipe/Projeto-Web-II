package com.bti.projetoweb2.controllers;

import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.services.FuncionarioService;
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
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "http://localhost:5173")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    @Operation(summary = "Lista todos os funcionários", description = "Retorna uma lista paginada de funcionários cadastrados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public Page<Funcionario> listarFuncionarios(@RequestParam(required = false) String nome, 
                                                @PageableDefault(size = 5) Pageable pageable) {
        return funcionarioService.listarTodos(nome, pageable);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtém um funcionário por ID", description = "Retorna os detalhes de um funcionário específico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Funcionário encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Funcionario> buscarFuncionarioPorId(@PathVariable Long id) {
        Funcionario funcionario = funcionarioService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
        return ResponseEntity.ok(funcionario);
    }

    @PostMapping
    @Operation(summary = "Cria um novo funcionário", description = "Cadastra um novo funcionário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Funcionário criado com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> salvarFuncionario(@RequestBody Funcionario funcionario) {
        try {
            Funcionario salvo = funcionarioService.salvar(funcionario);
            return ResponseEntity.ok(salvo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualiza um funcionário existente", description = "Atualiza os dados de um funcionário existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Funcionário atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<?> atualizarFuncionario(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            Funcionario atualizado = funcionarioService.atualizar(id, funcionario);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um funcionário", description = "Remove um funcionário do sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Funcionário deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Funcionário não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    public ResponseEntity<Void> deletarFuncionario(@PathVariable Long id) {
        try {
            funcionarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}