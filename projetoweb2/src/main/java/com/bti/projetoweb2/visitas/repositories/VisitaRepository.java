package com.bti.projetoweb2.visitas.repositories;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bti.projetoweb2.visitas.entities.Visita;

public interface VisitaRepository extends JpaRepository<Visita, Long> {

    // Busca por nome (ignorando maiúsculas/minúsculas)
    List<Visita> findByNomeContainingIgnoreCase(String parteNome);

    // Busca por data (com paginação)
    @Query("SELECT v FROM Visita v WHERE v.data LIKE %:data%")
    Page<Visita> searchByDataLike(@Param("data") String data, Pageable pageable);
}
