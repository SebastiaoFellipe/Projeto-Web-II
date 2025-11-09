package com.bti.projetoweb2.animais.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "animais")
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reabilitacao> reabilitacoes;

    @ManyToOne
    @JoinColumn(name = "habitat_id")
    private Habitat habitat;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    private String nomeCientifico;

    @NotBlank(message = "A familia é obrigatória.")
    private String familia;

    @NotBlank(message = "O genero é obrigatório.")
    private String genero;

    @NotBlank(message = "A especie é obrigatória.")
    private String especie;

    public enum Classificacao { 
        AMEACADO, 
        NAO_AMEACADO, 
        EXTINTO
    }

    @NotNull(message = "A classificação é obrigatória.")
    @Enumerated(EnumType.STRING)
    private Classificacao classificacao;

    @NotBlank(message = "A dieta é obrigatória.")
    private String dieta;

    @NotBlank(message = "O status de saúde é obrigatório.")
    private String statusSaude;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dataEntrada;

    @NotNull(message = "A idade é obrigatória.")
    private Integer idade;

    public Animal() {}

    public Animal(String nome, String nomeCientifico, String familia, String genero, String especie, Classificacao classificacao, String dieta, String statusSaude, Date dataEntrada, int idade) {
        this.nome = nome;
        this.nomeCientifico = nomeCientifico;
        this.familia = familia;
        this.genero = genero;
        this.especie = especie;
        this.classificacao = classificacao;
        this.dieta = dieta;
        this.statusSaude = statusSaude;
        this.dataEntrada = dataEntrada;
        this.idade = idade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCientifico() {
        return nomeCientifico;
    }

    public void setNomeCientifico(String nomeCientifico) {
        this.nomeCientifico = nomeCientifico;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public String getDieta() {
        return dieta;
    }

    public void setDieta(String dieta) {
        this.dieta = dieta;
    }

    public String getStatusSaude() {
        return statusSaude;
    }

    public void setStatusSaude(String statusSaude) {
        this.statusSaude = statusSaude;
    }

    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    public void setHabitat(Habitat habitat) {
        this.habitat = habitat;
    }
}
