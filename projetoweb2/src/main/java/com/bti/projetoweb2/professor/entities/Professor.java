package com.bti.projetoweb2.professor.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.bti.projetoweb2.pessoa.Pessoa;

@Entity
@Table(name = "professores")
public class Professor extends Pessoa {

    @NotBlank(message = "A disciplina é obrigatória.")
    private String disciplina;

    @NotNull(message = "O nível de ensino é obrigatório.")
    @Enumerated(EnumType.STRING)
    private NivelEnsino nivelEnsino;


    public Professor() {}

    public Professor(String nome, String cpf, String disciplina, NivelEnsino nivelEnsino){
        this.disciplina = disciplina;
        this.nivelEnsino = nivelEnsino;
    }

    public String getDisciplina() { return disciplina; }
    public void setDisciplina(String disciplina) { this.disciplina = disciplina; }

    public NivelEnsino getNivelEnsino() { return nivelEnsino; }
    public void setNivelEnsino(NivelEnsino nivelEnsino) { this.nivelEnsino = nivelEnsino; }
    
}
