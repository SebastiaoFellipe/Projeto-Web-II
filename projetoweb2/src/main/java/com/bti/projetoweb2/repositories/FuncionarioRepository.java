package com.bti.projetoweb2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	// Verifica se já existe algum funcionário relacionado a esse usuário
   boolean existsByUsuario_Id(Integer usuarioId);
}