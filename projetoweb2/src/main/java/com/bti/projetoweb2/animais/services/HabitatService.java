package com.bti.projetoweb2.animais.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bti.projetoweb2.animais.entities.Habitat;
import com.bti.projetoweb2.animais.repositories.HabitatRepository;

@Service
public class HabitatService {
    private final HabitatRepository habitatRepository;

    public HabitatService(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }

    public List<Habitat> listarTodos() {
        return habitatRepository.findAll();
    }

    public Habitat buscarPorId(Long id) {
        return habitatRepository.findById(id).orElse(null);
    }
}
