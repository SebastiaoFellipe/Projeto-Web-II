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

import com.bti.projetoweb2.animais.entities.Habitat;
import com.bti.projetoweb2.animais.services.HabitatService;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {
    @Autowired
    private final HabitatService habitatService;

    public HabitatController(HabitatService habitatService) {
        this.habitatService = habitatService;
    }

    @GetMapping//http://localhost:8080/api/habitats
    public List<Habitat> listarTodos() {
        return habitatService.listarTodos();
    }

    @GetMapping("/{id}")//http://localhost:8080/api/habitats/1
    public Habitat buscarPorId(@PathVariable Long id) {
        return habitatService.buscarPorId(id);
    }

    @PostMapping//http://localhost:8080/api/habitats
    public Habitat salvar(@RequestBody Habitat habitat) {
        return habitatService.salvar(habitat);
    }

    @PutMapping("/{id}")//http://localhost:8080/api/habitats/1
    public Habitat atualizar(@PathVariable Long id, @RequestBody Habitat habitatAtualizado) {
        return habitatService.atualizar(id, habitatAtualizado);
    }

    @DeleteMapping("/{id}")//http://localhost:8080/api/habitats/1
    public void deletar(@PathVariable Long id) {
        habitatService.deletar(id);
    }
}
