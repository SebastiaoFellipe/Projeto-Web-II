package com.bti.projetoweb2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bti.projetoweb2.entities.Professor;
import com.bti.projetoweb2.repositories.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Page<Professor> listarTodos(String nome, Pageable pageable) {
        if (nome != null && !nome.isEmpty()) {
            return professorRepository.findByNomeContainingIgnoreCase(nome, pageable);
        }
        return professorRepository.findAll(pageable);
    }

    public Optional<Professor> buscarPorId(Long id) {
        return professorRepository.findById(id);
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor atualizar(Long id, Professor professorAtualizado) {
        return professorRepository.findById(id)
                .map(professor -> {
                    professor.setNome(professorAtualizado.getNome());
                    professor.setCpf(professorAtualizado.getCpf());
                    professor.setAreaAplicada(professorAtualizado.getAreaAplicada());
                    professor.setNivelAcademico(professorAtualizado.getNivelAcademico());
                    return professorRepository.save(professor);
                })
                .orElseThrow(() -> new RuntimeException("Professor não encontrado com ID: " + id));
    }

    public void deletar(Long id) {
        if (!professorRepository.existsById(id)) {
            throw new RuntimeException("Professor não encontrado com ID: " + id);
        }
        professorRepository.deleteById(id);
    }
}
