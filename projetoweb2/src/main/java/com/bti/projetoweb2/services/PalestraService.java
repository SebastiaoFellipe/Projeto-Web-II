package com.bti.projetoweb2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Palestra;
import com.bti.projetoweb2.repositories.PalestraRepository;
import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.repositories.FuncionarioRepository;

@Service
public class PalestraService {

    @Autowired
    private PalestraRepository palestraRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // LISTAR PALESTRAS
    public Page<Palestra> listarPalestra(Pageable pageable) {
        return palestraRepository.findAll(pageable);
    }

    // CRIAR/ATUALIZAR PALESTRAS
    public Palestra salvarPalestra(Palestra palestra) {
        if (palestra.getFuncionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(palestra.getFuncionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            palestra.setFuncionario(funcionario);
        }
        return palestraRepository.save(palestra);
    }

    // BUSCAR PALESTRA POR ID
    public Palestra buscarPorId(Integer id) {
        return palestraRepository.findById(id).orElse(null);
    }

    // DELETAR PALESTRA
    public void deletarPalestra(Integer id) {
        palestraRepository.deleteById(id);
    }

    // BUSCAR POR TEMA COM PAGINAÇÃO
    public Page<Palestra> buscarPorTema(String tema, Pageable pageable) {
        return palestraRepository.searchByTemaLike(tema, pageable);
    }
}


