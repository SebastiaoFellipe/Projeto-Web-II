package com.bti.projetoweb2.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bti.projetoweb2.entities.Animal;
import com.bti.projetoweb2.entities.Habitat;
import com.bti.projetoweb2.repositories.AnimalRepository;
import com.bti.projetoweb2.repositories.HabitatRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;
    private final HabitatRepository habitatRepository;

    public AnimalService(AnimalRepository animalRepository, HabitatRepository habitatRepository) {
        this.animalRepository = animalRepository;
        this.habitatRepository = habitatRepository;
    }

    public Page<Animal> listarTodos(Pageable pageable) {
        Page<Animal> animais = animalRepository.findAll(pageable);

        if (animais.isEmpty()) {
            throw new EntityNotFoundException("Nenhum animal encontrado");
        }

        return animais;
    }

    public Animal buscarPorId(Long id) {
        return animalRepository.findById(id).orElse(null);
    }

    public List<Animal> buscarPorEspecie(String especie) {
        return animalRepository.findByEspecie(especie);
    }

    public List<Animal> buscarPorStatusSaude(String statusSaude) {
        return animalRepository.findByStatusSaude(statusSaude);
    }

    public List<Animal> buscarPorClassificacao(Animal.Classificacao classificacao) {
        return animalRepository.findByClassificacao(classificacao);
    }

    public List<Animal> buscarPorHabitatId(Long habitatId) {
        return animalRepository.findByHabitatId(habitatId);
    }

    public List<Animal> buscarPorFamilia(String familia) {
        return animalRepository.findByFamilia(familia);
    }

    public Animal salvar(Animal animal) {
        if (animal.getHabitat() != null) {
            String descricao = animal.getHabitat().getDescricao();
            Double temperatura = animal.getHabitat().getTemperatura();
            Habitat habitatExistente = habitatRepository.findByDescricao(descricao);
            
            if (habitatExistente != null && temperatura.equals(habitatExistente.getTemperatura())) {
                animal.setHabitat(habitatExistente);
            } else {
                Habitat novoHabitat = habitatRepository.save(animal.getHabitat());
                animal.setHabitat(novoHabitat);
            }
        }
        return animalRepository.save(animal);
    }

    public Animal atualizar(Long id, Animal animalAtualizado) {
        return animalRepository.findById(id)
            .map(animal -> {
                if (animalAtualizado.getHabitat() != null) {
                    String descricao = animalAtualizado.getHabitat().getDescricao();
                    Double temperatura = animalAtualizado.getHabitat().getTemperatura();
                    Habitat habitatExistente = habitatRepository.findByDescricao(descricao);

                    if (habitatExistente != null && temperatura.equals(habitatExistente.getTemperatura())) {
                        animal.setHabitat(habitatExistente);
                    } else {
                        Habitat novoHabitat = habitatRepository.save(animalAtualizado.getHabitat());
                        animal.setHabitat(novoHabitat);
                    }
                } else {
                    animal.setHabitat(null);
                }

                animal.setNome(animalAtualizado.getNome());
                animal.setNomeCientifico(animalAtualizado.getNomeCientifico());
                animal.setEspecie(animalAtualizado.getEspecie());
                animal.setFamilia(animalAtualizado.getFamilia());
                animal.setGenero(animalAtualizado.getGenero());
                animal.setClassificacao(animalAtualizado.getClassificacao());
                animal.setDieta(animalAtualizado.getDieta());
                animal.setStatusSaude(animalAtualizado.getStatusSaude());
                animal.setDataEntrada(animalAtualizado.getDataEntrada());
                animal.setIdade(animalAtualizado.getIdade());

                return animalRepository.save(animal);
            })
        .orElseThrow(() -> new RuntimeException("Animal não encontrado."));
    }

    public void deletar(Long id) {
        animalRepository.deleteById(id);
    }
}
