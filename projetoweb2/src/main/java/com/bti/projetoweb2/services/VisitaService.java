package com.bti.projetoweb2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bti.projetoweb2.entities.Visita;
import com.bti.projetoweb2.repositories.VisitaRepository;
import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.repositories.FuncionarioRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Page<Visita> listarTodos(String nome, Pageable pageable) {
        if (nome != null && !nome.isEmpty()) {
            return visitaRepository.findByNomeContainingIgnoreCase(nome, pageable); 
        }
        return visitaRepository.findAll(pageable);
    }

    public Page<Visita> listarVisitas(Pageable pageable) {
        return visitaRepository.findAll(pageable);
    }

    @Transactional
    public Visita salvarVisita(Visita visita, List<Long> funcionarioIds) {
        Visita visitaSalva = visitaRepository.save(visita);

        if (funcionarioIds != null && !funcionarioIds.isEmpty()) {
            Set<Funcionario> funcionarios = new HashSet<>(funcionarioRepository.findAllById(funcionarioIds));
            visitaSalva.setFuncionarios(funcionarios);
        } else {
            visitaSalva.setFuncionarios(new HashSet<>());
        }

        return visitaRepository.save(visitaSalva);
    }

    public Visita buscarPorId(Integer id) {
        return visitaRepository.findById(id).orElse(null);
    }

    public void deletarVisita(Integer id) {
        visitaRepository.deleteById(id);
    }
}
