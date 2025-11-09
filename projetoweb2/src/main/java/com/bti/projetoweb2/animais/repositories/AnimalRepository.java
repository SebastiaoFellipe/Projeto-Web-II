package com.bti.projetoweb2.animais.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bti.projetoweb2.animais.entities.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
    List<Animal> findByEspecie(String especie);
    List<Animal> findByStatusSaude(String statusSaude);
    List<Animal> findByClassificacao(Animal.Classificacao classificacao);
    List<Animal> findByHabitatId(Long habitatId);
    List<Animal> findByFamilia(String familia);
}