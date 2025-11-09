package com.bti.projetoweb2.animais.controllers;

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

import com.bti.projetoweb2.animais.entities.Animal;
import com.bti.projetoweb2.animais.services.AnimalService;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    @Autowired
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping///http://localhost:8080/api/animais
    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }

    @GetMapping("/{id}")//http://localhost:8080/api/animais/1
    public Animal buscarPorId(@PathVariable Long id) {
        return animalService.buscarPorId(id);
    }

    @GetMapping("/especie/{especie}")//http://localhost:8080/api/animais/especie/ave
    public List<Animal> buscarPorEspecie(@PathVariable String especie) {
        return animalService.buscarPorEspecie(especie);
    }

    @GetMapping("/status-saude/{statusSaude}")//http://localhost:8080/api/animais/status-saude/Saud%C3%A1vel
    public List<Animal> buscarPorStatusSaude(@PathVariable String statusSaude) {
        return animalService.buscarPorStatusSaude(statusSaude);
    }

    @GetMapping("/classificacao/{classificacao}")//http://localhost:8080/api/animais/classificacao/AMEACADO
    public List<Animal> buscarPorClassificacao(@PathVariable Animal.Classificacao classificacao) {
        return animalService.buscarPorClassificacao(classificacao);
    }

    @GetMapping("/habitat/{habitatId}")//http://localhost:8080/api/animais/habitat/1
    public List<Animal> buscarPorHabitatId(@PathVariable Long habitatId) {
        return animalService.buscarPorHabitatId(habitatId);
    }

    @GetMapping("/familia/{familia}")//http://localhost:8080/api/animais/familia/Callichthyidae
    public List<Animal> buscarPorFamilia(@PathVariable String familia) {
        return animalService.buscarPorFamilia(familia);
    }

    @PostMapping //http://localhost:8080/api/animais
    public Animal salvar(@RequestBody Animal animal) {
        return animalService.salvar(animal);
    }

    @PutMapping("/{id}")//http://localhost:8080/api/animais/1
    public Animal atualizar(@PathVariable Long id, @RequestBody Animal animalAtualizado) {
        return animalService.atualizar(id, animalAtualizado);
    }

    @DeleteMapping("/{id}")//http://localhost:8080/api/animais/1
    public void deletar(@PathVariable Long id) {
        animalService.deletar(id);
    }
}
