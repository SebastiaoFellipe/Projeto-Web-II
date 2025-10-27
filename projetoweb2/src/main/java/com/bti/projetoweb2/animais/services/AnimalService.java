package com.bti.projetoweb2.animais.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bti.projetoweb2.animais.entities.Animal;
import com.bti.projetoweb2.animais.repositories.AnimalRepository;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
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
        return animalRepository.save(animal);
    }

    public Animal atualizar(Long id, Animal animalAtualizado) {
        return animalRepository.findById(id)
                .map(animal -> {
                    animal.setNome(animalAtualizado.getNome());
                    animal.setHabitat(animalAtualizado.getHabitat());
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
