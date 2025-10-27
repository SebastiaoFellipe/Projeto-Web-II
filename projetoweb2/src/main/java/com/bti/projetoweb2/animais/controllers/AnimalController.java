package com.bti.projetoweb2.animais.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bti.projetoweb2.animais.entities.Animal;
import com.bti.projetoweb2.animais.services.AnimalService;

@RestController
@RequestMapping("/api/animais")
public class AnimalController {
    private final AnimalService animalService;

    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping
    public List<Animal> listarTodos() {
        return animalService.listarTodos();
    }
}
