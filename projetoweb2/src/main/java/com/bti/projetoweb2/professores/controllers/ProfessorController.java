package com.bti.projetoweb2.professores.controllers;

import com.bti.projetoweb2.professores.entities.Professor;
import com.bti.projetoweb2.professores.services.ProfessorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }


    @GetMapping
    public String listProfessores(Model model) {
        model.addAttribute("professores", professorService.listarTodos());
        return "professores/list";
    }

    @GetMapping("/cadastrar")
    public String showCreateForm(Model model) {
        model.addAttribute("professor", new Professor());
        model.addAttribute("isEdit", false);
        return "professores/form";
    }

    @PostMapping("/salvar")
    public String saveProfessor(@ModelAttribute Professor professor, RedirectAttributes redirectAttributes) {
        try {
            professorService.salvar(professor);
            redirectAttributes.addFlashAttribute("message", "Professor cadastrado com sucesso!");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Erro: " + e.getMessage());
        }
        return "redirect:/professores";
    }

    @GetMapping("/editar/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Professor> professorOpt = professorService.buscarPorId(id);

        if (professorOpt.isPresent()) {
            model.addAttribute("professor", professorOpt.get());
            model.addAttribute("isEdit", true);
            return "professores/form";
        } else {
            redirectAttributes.addFlashAttribute("error", "Professor não encontrado.");
            return "redirect:/professores";
        }
    }

    @GetMapping("/deletar/{id}")
    public String deleteProfessor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            professorService.deletar(id);
            redirectAttributes.addFlashAttribute("message", "Professor deletado com sucesso.");
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("error", "Erro ao deletar: " + e.getMessage());
        }
        return "redirect:/professores";
    }
}