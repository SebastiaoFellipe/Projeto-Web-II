package com.bti.projetoweb2.funcionarios.controllers;

import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.services.FuncionarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    public FuncionarioController(FuncionarioService funcionarioService) {
        this.funcionarioService = funcionarioService;
    }

    
    @GetMapping
    public String listFuncionarios(Model model) {
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "funcionarios/list";
    }

    
    @GetMapping("/cadastrar")
    public String showCreateForm(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        model.addAttribute("isEdit", false);
        return "funcionarios/form";
    }

    
    @PostMapping("/salvar")
    public String saveFuncionario(@ModelAttribute Funcionario funcionario, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.salvar(funcionario);
            redirectAttributes.addFlashAttribute("message", "Funcionário cadastrado com sucesso!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Erro: " + e.getMessage());
        }
        return "redirect:/funcionarios";
    }

    
    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Funcionario> funcionarioOpt = funcionarioService.buscarPorId(id);

        if (funcionarioOpt.isPresent()) {
            model.addAttribute("funcionario", funcionarioOpt.get());
            model.addAttribute("isEdit", true);
            return "funcionarios/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Funcionário não encontrado.");
            return "redirect:/funcionarios";
        }
    }

    
    @GetMapping("/deletar/{id}")
    public String deleteFuncionario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            funcionarioService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "Funcionário deletado com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao deletar: " + e.getMessage());
        }
        return "redirect:/funcionarios";
    }
}