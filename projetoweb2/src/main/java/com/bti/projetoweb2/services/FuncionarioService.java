package com.bti.projetoweb2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.repositories.FuncionarioRepository;


@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    public void salvarFuncionario(Funcionario funcionario) {
        // Valida se já existe o funcionário
        if (funcionario.getUsuario() != null && funcionarioRepository.existsByUsuario_Id(funcionario.getUsuario().getId())) {
            throw new IllegalArgumentException("Já existe um funcionário vinculado a este usuário.");
        }

        funcionarioRepository.save(funcionario);
    }
}

