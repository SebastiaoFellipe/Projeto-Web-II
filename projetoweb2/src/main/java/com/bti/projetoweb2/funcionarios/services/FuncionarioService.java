package com.bti.projetoweb2.funcionarios.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {

    private final FuncionarioRepository funcionarioRepository;

    public FuncionarioService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
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
