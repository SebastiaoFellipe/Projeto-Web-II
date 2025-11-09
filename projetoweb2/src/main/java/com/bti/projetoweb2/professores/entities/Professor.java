package com.bti.projetoweb2.professores.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.bti.projetoweb2.pessoa.Pessoa;

@Entity
@Table(name = "professores")
public class Professor extends Pessoa {

    @NotBlank(message = "A disciplina é obrigatória.")
    private String areaAplicada;

    @NotNull(message = "O nível de ensino é obrigatório.")
    @Enumerated(EnumType.STRING)
    private NivelAcademico nivelAcademico;


    public Professor() {}

    public Professor(String nome, String cpf, String disciplina, NivelAcademico nivelEnsino){
        super(nome, cpf); 
        this.areaAplicada = disciplina;
        this.nivelAcademico = nivelEnsino;
    }

    public String getAreaAplicada() { return areaAplicada; }
    public void setAreaAplicada(String disciplina) { this.areaAplicada = disciplina; }

    public NivelAcademico getNivelAcademico() { return nivelAcademico; }
    public void setNivelAcademico(NivelAcademico nivelEnsino) { this.nivelAcademico = nivelEnsino; }
    
}