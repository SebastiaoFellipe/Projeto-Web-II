package com.bti.projetoweb2.funcionarios.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import com.bti.projetoweb2.visitas.entities.Visita;
import com.bti.projetoweb2.palestras.entities.Palestra;
import com.bti.projetoweb2.pessoa.Pessoa;

@Entity
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @NotBlank(message = "O cargo é obrigatório.")
    private String cargo;

    @NotNull(message = "O tipo de vínculo é obrigatório.")
    @Enumerated(EnumType.STRING)
    private TipoVinculo tipoVinculo;

    @ManyToMany(mappedBy = "funcionarios")
    private Set<Visita> visitas = new HashSet<>();
    
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Palestra> palestras = new HashSet<>();
    
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

    public Set<Visita> getVisitas() { return visitas; }
    public void setVisitas(Set<Visita> visitas) { this.visitas = visitas; }
    
    public Set<Palestra> getPalestras() { return palestras; }
    public void setPalestras(Set<Palestra> palestras) { this.palestras = palestras; }
}
