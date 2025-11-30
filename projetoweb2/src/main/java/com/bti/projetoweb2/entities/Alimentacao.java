package com.bti.projetoweb2.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "alimentacoes")
public class Alimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O tipo de alimentação é obrigatório.")
    private String tipoAlimentacao;

    @NotNull(message = "A quantidade é obrigatória.")
    private Double quantidade;

    @NotNull(message = "A data de alimentação é obrigatória.")
    private LocalDate dataAlimentacao;

    private String observacoes;

    @NotNull(message = "O funcionário é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    @NotNull(message = "O animal é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;

    @ManyToOne
    @JoinColumn(name = "estoque_id")
    private Estoque estoque;

    public Alimentacao() {}

    public Alimentacao(@NotBlank(message = "O tipo de alimentação é obrigatório.") String tipoAlimentacao,
            @NotNull(message = "A quantidade é obrigatória.") Double quantidade,
            @NotNull(message = "A data de alimentação é obrigatória.") LocalDate dataAlimentacao, String observacoes,
            @NotNull(message = "O funcionário é obrigatório.") Funcionario funcionario,
            @NotNull(message = "O animal é obrigatório.") Animal animal, Estoque estoque) {
        this.tipoAlimentacao = tipoAlimentacao;
        this.quantidade = quantidade;
        this.dataAlimentacao = dataAlimentacao;
        this.observacoes = observacoes;
        this.funcionario = funcionario;
        this.animal = animal;
        this.estoque = estoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoAlimentacao() {
        return tipoAlimentacao;
    }

    public void setTipoAlimentacao(String tipoAlimentacao) {
        this.tipoAlimentacao = tipoAlimentacao;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDate getDataAlimentacao() {
        return dataAlimentacao;
    }

    public void setDataAlimentacao(LocalDate dataAlimentacao) {
        this.dataAlimentacao = dataAlimentacao;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Estoque getEstoque() {
        return estoque;
    }

    public void setEstoque(Estoque estoque) {
        this.estoque = estoque;
    }

    
}
