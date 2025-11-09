package com.bti.projetoweb2.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    private String nome;

    private String data;
    private String hora;
    private String instituicao;
    private String tipoInstituicao;
    private String professorResponsavel;

    @Min(3)
    @Max(21)
    private int qtdAlunos;

    private String telefone;

    
    @ManyToMany
    @JoinTable(
        name = "funcionario_visita",
        joinColumns = @JoinColumn(name = "visita_id"),
        inverseJoinColumns = @JoinColumn(name = "funcionario_id")
    )
    private Set<Funcionario> funcionarios = new HashSet<>();

    public Visita() {}

    public Visita(int id, String nome, String data, String hora, String instituicao, 
                  String professorResponsavel, int qtdAlunos, String telefone) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.hora = hora;
        this.instituicao = instituicao;
        this.professorResponsavel = professorResponsavel;
        this.qtdAlunos = qtdAlunos;
        this.telefone = telefone;
    }

  
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHora() { return hora; }
    public void setHora(String hora) { this.hora = hora; }

    public String getInstituicao() { return instituicao; }
    public void setInstituicao(String instituicao) { this.instituicao = instituicao; }

    public String getTipoInstituicao() { return tipoInstituicao; }
    public void setTipoInstituicao(String tipoInstituicao) { this.tipoInstituicao = tipoInstituicao; }

    public String getProfessorResponsavel() { return professorResponsavel; }
    public void setProfessorResponsavel(String professorResponsavel) { this.professorResponsavel = professorResponsavel; }

    public int getQtdAlunos() { return qtdAlunos; }
    public void setQtdAlunos(int qtdAlunos) { this.qtdAlunos = qtdAlunos; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public Set<Funcionario> getFuncionarios() { return funcionarios; }
    public void setFuncionarios(Set<Funcionario> funcionarios) { this.funcionarios = funcionarios; }
}


