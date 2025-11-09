package com.bti.projetoweb2.funcionarios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import com.bti.projetoweb2.pessoa.Pessoa;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @NotBlank(message = "O cargo é obrigatório.")
    private String cargo;

    @NotNull(message = "O tipo de vínculo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoVinculo tipoVinculo;

    public Funcionario() {}

    public Funcionario(String nome, String cpf, String cargo, TipoVinculo tipoVinculo) {
        super(nome, cpf);
        this.cargo = cargo;
        this.tipoVinculo = tipoVinculo;
    }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public TipoVinculo getTipoVinculo() { return tipoVinculo; }
    public void setTipoVinculo(TipoVinculo tipoVinculo) { this.tipoVinculo = tipoVinculo; }
}
