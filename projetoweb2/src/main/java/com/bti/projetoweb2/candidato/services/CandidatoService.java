package com.bti.projetoweb2.candidato.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bti.projetoweb2.candidato.entities.Candidato;
import com.bti.projetoweb2.candidato.repositories.CandidatoRepository;

@Service
public class CandidatoService {
    private final CandidatoRepository candidatoRepository;

    public CandidatoService(CandidatoRepository candidatoRepository) {
        this.candidatoRepository = candidatoRepository;
    }

    public List<Candidato> listarTodos() {
        return candidatoRepository.findAll();
    }

    public Candidato buscarPorId(Long id) {
        return candidatoRepository.findById(id).orElseThrow(() -> new RuntimeException("Candidato não encontrado."));
    }

    public Candidato salvar(Candidato candidato) {
        if (candidatoRepository.existsByCpf(candidato.getCpf())) {
            throw new RuntimeException("Já existe um candidato cadastrado com este CPF.");
        }
        return candidatoRepository.save(candidato);
    }

    public Candidato atualizar(Long id, Candidato candidatoAtualizado) {
        return candidatoRepository.findById(id)
            .map(candidato -> {
                candidato.setNome(candidatoAtualizado.getNome());
                candidato.setCpf(candidatoAtualizado.getCpf());
                candidato.setFormacaoAcademica(candidatoAtualizado.getFormacaoAcademica());
                candidato.setEmail(candidatoAtualizado.getEmail());
                return candidatoRepository.save(candidato);
            })
            .orElseThrow(() -> new RuntimeException("Candidato não encontrado."));
    }

    public void deletar(Long id) {
        if (!candidatoRepository.existsById(id)) {
            throw new RuntimeException("Candidato não encontrado com ID: " + id);
        }
        candidatoRepository.deleteById(id);
    }
}
