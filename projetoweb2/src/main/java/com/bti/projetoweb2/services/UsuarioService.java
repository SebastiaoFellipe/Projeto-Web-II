package com.bti.projetoweb2.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bti.projetoweb2.entities.Usuario;
import com.bti.projetoweb2.repositories.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public void salvarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
    }
    
    
}

