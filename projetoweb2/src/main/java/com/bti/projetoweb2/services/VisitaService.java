package com.bti.projetoweb2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.entities.Visita;
import com.bti.projetoweb2.repositories.FuncionarioRepository;
import com.bti.projetoweb2.repositories.VisitaRepository;

import java.util.HashSet;
import java.util.List;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // LISTAR
    public Page<Visita> listarVisitas(Pageable pageable) {
        return visitaRepository.findAll(pageable);
    }

    // CRIAR/ATUALIZAR — agora aceita funcionários
    public void salvarVisita(Visita visita, List<Integer> funcionarioIds) {
        if (funcionarioIds != null && !funcionarioIds.isEmpty()) {
            List<Funcionario> funcionarios = funcionarioRepository.findAllById(funcionarioIds);
            visita.setFuncionarios(new HashSet<>(funcionarios)); 
        }
        visitaRepository.save(visita);
    }

    // BUSCAR POR ID
    public Visita buscarPorId(Integer id) {
        return visitaRepository.findById(id).orElse(null);
    }

    // DELETAR
    public void deletarVisita(Integer id) {
        visitaRepository.deleteById(id);
    }

    // BUSCAR POR DATA (com paginação)
    public Page<Visita> buscarPorData(String data, Pageable pageable) {
        return visitaRepository.searchByDataLike(data, pageable);
    }
}
