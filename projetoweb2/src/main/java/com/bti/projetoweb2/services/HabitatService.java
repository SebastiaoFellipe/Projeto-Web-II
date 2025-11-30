package com.bti.projetoweb2.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Habitat;
import com.bti.projetoweb2.repositories.HabitatRepository;

@Service
public class HabitatService {
    private final HabitatRepository habitatRepository;

    public HabitatService(HabitatRepository habitatRepository) {
        this.habitatRepository = habitatRepository;
    }

    public Page<Habitat> listarTodos(Pageable pageable) {
        Page<Habitat> habitats = habitatRepository.findAll(pageable);

        return habitats;
    }

    public Habitat buscarPorId(Long id) {
        return habitatRepository.findById(id).orElse(null);
    }

    public Habitat salvar(Habitat habitat) {
        return habitatRepository.save(habitat);
    }

    public Habitat atualizar(Long id, Habitat habitatAtualizado) {
        return habitatRepository.findById(id)
            .map(habitat -> {
                habitat.setDescricao(habitatAtualizado.getDescricao());
                habitat.setTipoAmbiente(habitatAtualizado.getTipoAmbiente());
                habitat.setTemperatura(habitatAtualizado.getTemperatura());
                return habitatRepository.save(habitat);
            })
            .orElse(null);
    }

    public void deletar(Long id) {
        habitatRepository.deleteById(id);
    }
}
