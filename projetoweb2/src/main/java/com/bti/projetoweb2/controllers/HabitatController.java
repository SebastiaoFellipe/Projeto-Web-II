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

import com.bti.projetoweb2.entities.Habitat;
import com.bti.projetoweb2.services.HabitatService;

@RestController
@RequestMapping("/api/habitats")
public class HabitatController {
    @Autowired
    private final HabitatService habitatService;

    public HabitatController(HabitatService habitatService) {
        this.habitatService = habitatService;
    }

    @GetMapping
    public List<Habitat> listarTodos() {
        return habitatService.listarTodos();
    }

    @GetMapping("/{id}")
    public Habitat buscarPorId(@PathVariable Long id) {
        return habitatService.buscarPorId(id);
    }

    @PostMapping
    public Habitat salvar(@RequestBody Habitat habitat) {
        return habitatService.salvar(habitat);
    }

    @PutMapping("/{id}")
    public Habitat atualizar(@PathVariable Long id, @RequestBody Habitat habitatAtualizado) {
        return habitatService.atualizar(id, habitatAtualizado);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        habitatService.deletar(id);
    }
}
