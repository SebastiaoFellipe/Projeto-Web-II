package com.bti.projetoweb2.visitas.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;
import com.bti.projetoweb2.funcionarios.entities.Funcionario;

@Entity
@Table(name = "visitas")
public class Visita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    private String data;
    private String hora;
    private String instituicao;
    private String tipoInstituicao;
    private String professorResponsavel;

    @Min(value = 3, message = "A quantidade mínima de alunos é 3")
    @Max(value = 21, message = "A quantidade máxima de alunos é 21")
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

    // Getters e setters
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
