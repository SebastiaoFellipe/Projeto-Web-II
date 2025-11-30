package com.bti.projetoweb2.entities;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O código do produto é obrigatório.")
    private String codigoProduto;

    @NotBlank(message = "O nome do produto é obrigatório.")
    private String nomeProduto;

    @NotNull(message = "A quantidade é obrigatória.")
    private Integer quantidade;

    @NotBlank(message = "A unidade de medida é obrigatória.")
    private String unidadeMedida;

    @NotNull(message = "A data de validade é obrigatória.")
    private LocalDate dataValidade;

    @OneToMany(mappedBy = "estoque")
    @JsonIgnore
    private List<Alimentacao> alimentacoes;

    public Estoque() {}

    public Estoque(String codigoProduto, String nomeProduto, Integer quantidade, String unidadeMedida,
            LocalDate dataValidade) {
        this.codigoProduto = codigoProduto;
        this.nomeProduto = nomeProduto;
        this.quantidade = quantidade;
        this.unidadeMedida = unidadeMedida;
        this.dataValidade = dataValidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(String codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public List<Alimentacao> getAlimentacoes() {
        return alimentacoes;
    }

    public void setAlimentacoes(List<Alimentacao> alimentacoes) {
        this.alimentacoes = alimentacoes;
    }

    
}
