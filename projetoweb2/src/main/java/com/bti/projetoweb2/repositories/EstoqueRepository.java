package com.bti.projetoweb2.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bti.projetoweb2.entities.Estoque;

public interface EstoqueRepository extends JpaRepository<Estoque, Long> {
    List<Estoque> findByNomeProdutoContainingIgnoreCase(String nome);

    // Query JPQL para buscar itens com estoque baixo
    @Query("SELECT e FROM Estoque e WHERE e.quantidade < :minimo")
    List<Estoque> findItensComEstoqueBaixo(@Param("minimo") int minimo);
}
