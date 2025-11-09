package com.bti.projetoweb2.animais.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
