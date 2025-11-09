package com.bti.projetoweb2.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bti.projetoweb2.entities.Palestra;

public interface PalestraRepository extends JpaRepository<Palestra, Integer> {
	
	// Busca automática por nome (contém parte do texto)
    public List<Palestra> findByTemaContainingIgnoreCase(String parteTema);

   
    @Query("SELECT p FROM Palestra p WHERE p.tema LIKE %:tema%")
    Page<Palestra> searchByTemaLike(@Param("tema") String tema, Pageable pageable);

}
