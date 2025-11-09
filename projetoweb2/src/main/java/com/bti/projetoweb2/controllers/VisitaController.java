package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.entities.Visita;
import com.bti.projetoweb2.services.FuncionarioService;
import com.bti.projetoweb2.services.VisitaService;

import jakarta.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/visitas")
public class VisitaController {

    @Autowired
    private VisitaService visitaService;

    @Autowired
    private FuncionarioService funcionarioService;

    // LISTAR VISITAS
    @GetMapping
    public String listarVisitas(@PageableDefault(size = 8, sort = "id") Pageable pageable, Model vmodel) {
        adicionarVisitas(pageable, vmodel);
        return "visitas";
    }

    private void adicionarVisitas(Pageable pageable, Model vmodel) {
        Page<Visita> paginaVisitas = visitaService.listarVisitas(pageable);
        vmodel.addAttribute("paginaVisitas", paginaVisitas);
        vmodel.addAttribute("visitas", paginaVisitas.getContent());
    }

    // FORMULÁRIO NOVA VISITA
    @GetMapping("/nova")
    public String mostrarFormulario(Model vmodel) {
        vmodel.addAttribute("visita", new Visita());
        vmodel.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "novaVisita";
    }

    // SALVAR NOVA VISITA
    @PostMapping
    public String salvarVisita(
        @Valid @ModelAttribute("visita") Visita visita,
        @RequestParam(value = "funcionarioIds", required = false) List<Integer> funcionarioIds
    ) {
        visitaService.salvarVisita(visita, funcionarioIds);
        return "redirect:/visitas";
    }

    // EDITAR VISITA
    @GetMapping("/editar/{id}")
    public String editarVisita(@PathVariable int id, Model vmodel) {
        Visita visita = visitaService.buscarPorId(id);
        if (visita == null) {
            return "redirect:/visitas";
        }
        vmodel.addAttribute("visita", visita);
        vmodel.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "editarVisita";
    }

    // ATUALIZAR VISITA
    @PostMapping("/atualizar")
    public String atualizarVisita(
        @Valid @ModelAttribute("visita") Visita visita,
        @RequestParam(value = "funcionarioIds", required = false) List<Integer> funcionarioIds
    ) {
        visitaService.salvarVisita(visita, funcionarioIds);
        return "redirect:/visitas";
    }

    // DELETAR VISITA
    @GetMapping("/deletar/{id}")
    public String deletarVisita(@PathVariable int id) {
        visitaService.deletarVisita(id);
        return "redirect:/visitas";
    }

    // BUSCAR POR DATA + PAGINAÇÃO
    @GetMapping("/buscar")
    public String buscarVisitaPorData(
            @RequestParam("data") String data,
            @PageableDefault(size = 8, sort = "id") Pageable pageable,
            Model model) {

        prepararBusca(data, pageable, model);
        return "visitas";
    }

    private void prepararBusca(String data, Pageable pageable, Model vmodel) {
        Page<Visita> paginaVisitas = visitaService.buscarPorData(data, pageable);
        vmodel.addAttribute("paginaVisitas", paginaVisitas);
        vmodel.addAttribute("visitas", paginaVisitas.getContent());
        vmodel.addAttribute("nomeBusca", data);
        vmodel.addAttribute("isSearch", true);
    }
}
