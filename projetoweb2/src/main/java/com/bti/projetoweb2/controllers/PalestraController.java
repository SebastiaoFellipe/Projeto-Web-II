package com.bti.projetoweb2.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bti.projetoweb2.entities.Palestra;
import com.bti.projetoweb2.services.FuncionarioService;
import com.bti.projetoweb2.services.PalestraService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/palestras")
public class PalestraController {

    @Autowired
    private PalestraService palestraService;

    @Autowired
    private FuncionarioService funcionarioService;

    // LISTAR TODAS AS PALESTRAS
    @GetMapping
    public String listarPalestras(@PageableDefault(size = 8, sort = "id") Pageable pageable, Model pmodel) {
        adicionarPalestras(pageable, pmodel);
        return "palestras";
    }

    private void adicionarPalestras(Pageable pageable, Model pmodel) {
        Page<Palestra> paginaPalestras = palestraService.listarPalestra(pageable);
        pmodel.addAttribute("paginaPalestras", paginaPalestras);
        pmodel.addAttribute("palestras", paginaPalestras.getContent());
    }

    // FORMULÁRIO PARA NOVA PALESTRA
    @GetMapping("/nova")
    public String mostrarFormulario(Model pmodel) {
        pmodel.addAttribute("palestra", new Palestra());
        pmodel.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "novaPalestra";
    }

    // SALVAR NOVA PALESTRA
    @PostMapping
    public String salvarPalestra(@Valid @ModelAttribute("palestra") Palestra palestra) {
        palestraService.salvarPalestra(palestra);
        return "redirect:/palestras";
    }

    // FORMULÁRIO DE EDIÇÃO DE PALESTRA
    @GetMapping("/editar/{id}")
    public String editarPalestra(@PathVariable int id, Model pmodel) {
        Palestra palestra = palestraService.buscarPorId(id);

        if (palestra == null) {
            // Evita erro caso o ID não exista
            return "redirect:/palestras";
        }

        // Preenche o campo de funcionário no formulário
        if (palestra.getFuncionario() != null) {
            palestra.setFuncionarioId(palestra.getFuncionario().getId());
        }

        pmodel.addAttribute("palestra", palestra);
        pmodel.addAttribute("funcionarios", funcionarioService.listarFuncionarios());
        return "editarPalestra";
    }

    // ATUALIZAR PALESTRA EXISTENTE
    @PostMapping("/atualizar")
    public String atualizarPalestra(@Valid @ModelAttribute("palestra") Palestra palestra) {
        palestraService.salvarPalestra(palestra);
        return "redirect:/palestras";
    }

    // DELETAR PALESTRA
    @GetMapping("/deletar/{id}")
    public String deletarPalestra(@PathVariable int id) {
        palestraService.deletarPalestra(id);
        return "redirect:/palestras";
    }

    // BUSCAR PALESTRA POR TEMA
    @GetMapping("/buscar")
    public String buscarPalestraPorNome(
            @RequestParam("tema") String tema,
            @PageableDefault(size = 8, sort = "id") Pageable pageable,
            Model pmodel) {

        prepararBusca(tema, pageable, pmodel);
        return "palestras";
    }

    private void prepararBusca(String tema, Pageable pageable, Model pmodel) {
        Page<Palestra> paginaPalestras = palestraService.buscarPorTema(tema, pageable);
        pmodel.addAttribute("paginaPalestras", paginaPalestras);
        pmodel.addAttribute("palestras", paginaPalestras.getContent());
        pmodel.addAttribute("nomeBusca", tema);
        pmodel.addAttribute("isSearch", true);
    }
}
