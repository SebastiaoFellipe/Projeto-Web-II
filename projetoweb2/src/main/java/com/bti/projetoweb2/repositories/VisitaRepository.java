package com.bti.projetoweb2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bti.projetoweb2.entities.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {
    
    // Busca automática por nome (contém parte do texto)
    public List<Visita> findByNomeContainingIgnoreCase(String parteNome);

  
    
    // Busca por data contendo parte do texto (com paginação)
    @Query("SELECT v FROM Visita v WHERE v.data LIKE %:data%")
    Page<Visita> searchByDataLike(@Param("data") String data, Pageable pageable);
    
	
}

