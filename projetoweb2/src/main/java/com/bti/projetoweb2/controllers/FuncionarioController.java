package com.bti.projetoweb2.controllers;

import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.repositories.UsuarioRepository;
import com.bti.projetoweb2.services.FuncionarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/novo")
    public String novoFuncionario(Model fmodel) {
        fmodel.addAttribute("funcionario", new Funcionario());
        fmodel.addAttribute("usuarios", usuarioRepository.findAll());
        return "form"; 
    }
    
    @PostMapping("/salvar")
    public String salvarFuncionario(@ModelAttribute Funcionario funcionario, Model fmodel) {
        if (funcionario.getUsuarioId() != null) {
            funcionario.setUsuario(usuarioRepository.findById(funcionario.getUsuarioId()).orElse(null));
        }

        try {
            funcionarioService.salvarFuncionario(funcionario);
        } catch (IllegalArgumentException ex) {
            fmodel.addAttribute("erro", ex.getMessage());
            fmodel.addAttribute("funcionario", funcionario);
            fmodel.addAttribute("usuarios", usuarioRepository.findAll());
            return "form";
        }

        return "redirect:/funcionarios";
    }

    
    @GetMapping
    public String listarFuncionarios(Model fmodel) {
        fmodel.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "listar";
    }
}


