package com.bti.projetoweb2.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bti.projetoweb2.entities.Animal;
import com.bti.projetoweb2.services.AnimalService;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    @Autowired
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }

    @GetMapping("/{id}")
    public Animal buscarPorId(@PathVariable Long id) {
        return animalService.buscarPorId(id);
    }

    @GetMapping("/especie/{especie}")
    public List<Animal> buscarPorEspecie(@PathVariable String especie) {
        return animalService.buscarPorEspecie(especie);
    }

    @GetMapping("/status-saude/{statusSaude}")
    public List<Animal> buscarPorStatusSaude(@PathVariable String statusSaude) {
        return animalService.buscarPorStatusSaude(statusSaude);
    }

    @GetMapping("/classificacao/{classificacao}")
    public List<Animal> buscarPorClassificacao(@PathVariable Animal.Classificacao classificacao) {
        return animalService.buscarPorClassificacao(classificacao);
    }

    @GetMapping("/habitat/{habitatId}")
    public List<Animal> buscarPorHabitatId(@PathVariable Long habitatId) {
        return animalService.buscarPorHabitatId(habitatId);
    }

    @GetMapping("/familia/{familia}")
    public List<Animal> buscarPorFamilia(@PathVariable String familia) {
        return animalService.buscarPorFamilia(familia);
    }

    @PostMapping
    public Animal salvar(@RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    @PutMapping("/{id}")
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        return animalService.atualizar(id, animalAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        animalService.deletar(id);
    }
}
