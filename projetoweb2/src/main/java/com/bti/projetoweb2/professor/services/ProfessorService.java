package com.bti.projetoweb2.professor.services;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.bti.projetoweb2.professor.entities.Professor;
import com.bti.projetoweb2.professor.repositories.ProfessorRepository;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<Professor> listarTodos() {
        return professorRepository.findAll();
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
                    professor.setDisciplina(professorAtualizado.getDisciplina());
                    professor.setNivelEnsino(professorAtualizado.getNivelEnsino());
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
