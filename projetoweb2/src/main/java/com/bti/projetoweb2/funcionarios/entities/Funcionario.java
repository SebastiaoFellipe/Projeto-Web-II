package com.bti.projetoweb2.funcionarios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter exatamente 11 dígitos numéricos.")
    private String cpf;

    @NotBlank(message = "O cargo é obrigatório.")
    private String cargo;

    @NotBlank(message = "O setor é obrigatório.")
    private String setor;

    @NotNull(message = "O tipo de vínculo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoVinculo tipoVinculo;

    @NotBlank(message = "A especialidade é obrigatória.")
    private String especialidade;

    public Funcionario() {}

    public Funcionario(String nome, String cpf, String cargo, String setor, TipoVinculo tipoVinculo, String especialidade) {
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.setor = setor;
        this.tipoVinculo = tipoVinculo;
        this.especialidade = especialidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public TipoVinculo getTipoVinculo() { return tipoVinculo; }
    public void setTipoVinculo(TipoVinculo tipoVinculo) { this.tipoVinculo = tipoVinculo; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}
