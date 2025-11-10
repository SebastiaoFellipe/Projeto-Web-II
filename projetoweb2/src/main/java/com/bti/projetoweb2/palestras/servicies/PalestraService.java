package com.bti.projetoweb2.palestras.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.palestras.entities.Palestra;
import com.bti.projetoweb2.palestras.repositories.PalestraRepository;
import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.repositories.FuncionarioRepository;

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
    public void salvarPalestra(Palestra palestra) {
        if (palestra.getFuncionarioId() != null) {
            Funcionario funcionario = funcionarioRepository.findById(palestra.getFuncionarioId())
                    .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
            palestra.setFuncionario(funcionario);
        }
        palestraRepository.save(palestra);
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


