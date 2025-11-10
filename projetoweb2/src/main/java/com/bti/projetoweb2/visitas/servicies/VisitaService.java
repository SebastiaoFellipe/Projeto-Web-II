package com.bti.projetoweb2.visitas.servicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bti.projetoweb2.visitas.entities.Visita;
import com.bti.projetoweb2.visitas.repositories.VisitaRepository;
import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.repositories.FuncionarioRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class VisitaService {

    @Autowired
    private VisitaRepository visitaRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Page<Visita> listarVisitas(Pageable pageable) {
        return visitaRepository.findAll(pageable);
    }

    
    // garante que o ID da visita seja gerado antes de criar o vínculo ManyToMany.
     
    @Transactional
    public void salvarVisita(Visita visita, List<Long> funcionarioIds) {
        // Salva primeiro a visita para gerar o ID (necessário para a FK)
        Visita visitaSalva = visitaRepository.save(visita);

        // Se houver funcionários, associa depois
        if (funcionarioIds != null && !funcionarioIds.isEmpty()) {
            Set<Funcionario> funcionarios = new HashSet<>(funcionarioRepository.findAllById(funcionarioIds));
            visitaSalva.setFuncionarios(funcionarios);
        } else {
            visitaSalva.setFuncionarios(new HashSet<>());
        }

        // Salva novamente com as associações
        visitaRepository.save(visitaSalva);
    }

    public Visita buscarPorId(long id) {
        return visitaRepository.findById(id).orElse(null);
    }

    public void deletarVisita(long id) {
        visitaRepository.deleteById(id);
    }

    public Page<Visita> buscarPorData(String data, Pageable pageable) {
        return visitaRepository.searchByDataLike(data, pageable);
    }
}
