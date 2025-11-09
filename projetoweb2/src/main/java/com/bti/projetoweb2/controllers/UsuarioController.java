package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.entities.Usuario;
import com.bti.projetoweb2.services.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listarUsuarios(Model umodel) {
        umodel.addAttribute("usuarios", usuarioService.listarUsuarios());
        return "usuarios-listar";
    }

    @GetMapping("/novo")
    public String novoUsuarioForm(Model umodel) {
        umodel.addAttribute("usuario", new Usuario());
        return "usuarios-form";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@ModelAttribute Usuario usuario) {
        usuarioService.salvarUsuario(usuario);
        return "redirect:/usuarios";
    }
}

