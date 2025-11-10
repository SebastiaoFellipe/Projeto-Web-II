package com.bti.projetoweb2.visitas.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import com.bti.projetoweb2.visitas.entities.Visita;
import com.bti.projetoweb2.visitas.servicies.VisitaService;
import com.bti.projetoweb2.funcionarios.services.FuncionarioService;

import java.util.List;

@Controller
@RequestMapping("/visitas")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @Autowired
    private FuncionarioService funcionarioService;

    // LISTAR COM PAGINAÇÃO
    @GetMapping
    public String listar(@PageableDefault(size = 8, sort = "id") Pageable pageable, Model model) {
        Page<Visita> paginaVisitas = visitaService.listarVisitas(pageable);
        model.addAttribute("paginaVisitas", paginaVisitas);
        model.addAttribute("visitas", paginaVisitas.getContent());
        return "listaVisitas";
    }

    // FORM NOVO
    @GetMapping("/nova")
    public String novaVisita(Model model) {
        model.addAttribute("visita", new Visita());
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "novaVisita";
    }

    // SALVAR NOVA VISITA
    @PostMapping
    public String salvar(@Valid @ModelAttribute("visita") Visita visita,
                         @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds) {
        visitaService.salvarVisita(visita, funcionarioIds);
        return "redirect:/visitas";
    }

    // EDITAR
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable int id, Model model) {
        Visita visita = visitaService.buscarPorId(id);
        if (visita == null) return "redirect:/visitas";
        model.addAttribute("visita", visita);
        model.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "editarVisita";
    }

    // ATUALIZAR
    @PostMapping("/atualizar")
    public String atualizar(@Valid @ModelAttribute("visita") Visita visita,
                            @RequestParam(value = "funcionarioIds", required = false) List<Long> funcionarioIds) {
        visitaService.salvarVisita(visita, funcionarioIds);
        return "redirect:/visitas";
    }

    // DELETAR
    @GetMapping("/deletar/{id}")
    public String deletar(@PathVariable int id) {
        visitaService.deletarVisita(id);
        return "redirect:/visitas";
    }

    // BUSCAR POR DATA
    @GetMapping("/buscar")
    public String buscar(@RequestParam("data") String data,
                         @PageableDefault(size = 8, sort = "id") Pageable pageable,
                         Model model) {
        Page<Visita> paginaVisitas = visitaService.buscarPorData(data, pageable);
        model.addAttribute("paginaVisitas", paginaVisitas);
        model.addAttribute("visitas", paginaVisitas.getContent());
        model.addAttribute("nomeBusca", data);
        return "listaVisitas";
    }
}
