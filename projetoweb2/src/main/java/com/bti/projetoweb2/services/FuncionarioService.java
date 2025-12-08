package com.bti.projetoweb2.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public Page<Funcionario> listarTodos(String nome, Pageable pageable) {
        if (nome != null && !nome.isEmpty()) {
            return funcionarioRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        return funcionarioRepository.findAll(pageable);
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Funcionario salvar(Funcionario funcionario) {
        if (funcionarioRepository.existsByCpf(funcionario.getCpf())) {
            throw new RuntimeException("Já existe um funcionário cadastrado com este CPF.");
        }
        return funcionarioRepository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario funcionarioAtualizado) {
        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionario.setNome(funcionarioAtualizado.getNome());
                    funcionario.setCpf(funcionarioAtualizado.getCpf());
                    funcionario.setCargo(funcionarioAtualizado.getCargo());
                    funcionario.setTipoVinculo(funcionarioAtualizado.getTipoVinculo());
                    return funcionarioRepository.save(funcionario);
                })
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
    }

    public void deletar(Long id) {
        funcionarioRepository.deleteById(id);
    }
}
