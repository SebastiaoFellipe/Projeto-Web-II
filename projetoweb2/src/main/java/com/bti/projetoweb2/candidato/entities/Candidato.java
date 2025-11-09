package com.bti.projetoweb2.candidato.entities;

import com.bti.projetoweb2.pessoa.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "candidatos")
public class Candidato extends Pessoa {
    @NotBlank(message = "A formação acadêmica é obrigatória.")
    private String formacaoAcademica;

    @NotBlank(message = "O e-mail é obrigatório.")
    private String email;

    public Candidato() {}

    public Candidato(String nome, String cpf, String formacaoAcademica, String email) {
        super(nome, cpf);
        this.formacaoAcademica = formacaoAcademica;
        this.email = email;
    }

    public String getFormacaoAcademica() {
        return formacaoAcademica;
    }

    public void setFormacaoAcademica(String formacaoAcademica) {
        this.formacaoAcademica = formacaoAcademica;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
