package com.bti.projetoweb2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Page<Palestra> listarTodos(String tema, Pageable pageable) {
        if (tema != null && !tema.isEmpty()) {
            return palestraRepository.findByTemaContainingIgnoreCase(tema, pageable);
        }
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
}


