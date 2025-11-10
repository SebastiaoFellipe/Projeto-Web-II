package com.bti.projetoweb2.palestras.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.bti.projetoweb2.palestras.entities.Palestra;
import com.bti.projetoweb2.palestras.servicies.PalestraService;
import com.bti.projetoweb2.funcionarios.services.FuncionarioService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/palestras")
public class PalestraController {

    @Autowired
    private PalestraService palestraService;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public String listarPalestras(@PageableDefault(size = 8, sort = "id") Pageable pageable, Model pmodel) {
        Page<Palestra> pagina = palestraService.listarPalestra(pageable);
        pmodel.addAttribute("paginaPalestras", pagina);
        pmodel.addAttribute("palestras", pagina.getContent());
        return "palestras";
    }

    @GetMapping("/nova")
    public String mostrarFormulario(Model pmodel) {
        pmodel.addAttribute("palestra", new Palestra());
        pmodel.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "novaPalestra";
    }

    @PostMapping
    public String salvarPalestra(@Valid @ModelAttribute("palestra") Palestra palestra) {
        palestraService.salvarPalestra(palestra);
        return "redirect:/palestras";
    }

    @GetMapping("/editar/{id}")
    public String editarPalestra(@PathVariable int id, Model pmodel) {
        Palestra palestra = palestraService.buscarPorId(id);
        if (palestra == null) {
            return "redirect:/palestras";
        }
        if (palestra.getFuncionario() != null) {
            palestra.setFuncionarioId(palestra.getFuncionario().getId());
        }
        pmodel.addAttribute("palestra", palestra);
        pmodel.addAttribute("funcionarios", funcionarioService.listarTodos());
        return "editarPalestra";
    }

    @PostMapping("/atualizar")
    public String atualizarPalestra(@Valid @ModelAttribute("palestra") Palestra palestra) {
        palestraService.salvarPalestra(palestra);
        return "redirect:/palestras";
    }

    @GetMapping("/deletar/{id}")
    public String deletarPalestra(@PathVariable int id) {
        palestraService.deletarPalestra(id);
        return "redirect:/palestras";
    }

    @GetMapping("/buscar")
    public String buscarPalestraPorNome(@RequestParam("tema") String tema,
                                        @PageableDefault(size = 8, sort = "id") Pageable pageable,
                                        Model pmodel) {
        Page<Palestra> pagina = palestraService.buscarPorTema(tema, pageable);
        pmodel.addAttribute("paginaPalestras", pagina);
        pmodel.addAttribute("palestras", pagina.getContent());
        pmodel.addAttribute("nomeBusca", tema);
        pmodel.addAttribute("isSearch", true);
        return "palestras";
    }
}
