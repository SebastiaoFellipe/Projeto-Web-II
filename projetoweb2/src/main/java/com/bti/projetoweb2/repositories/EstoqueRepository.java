package com.bti.projetoweb2.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bti.projetoweb2.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    Page<Estoque> findByNomeProdutoContainingIgnoreCase(String nomeProduto, Pageable pageable);
}
