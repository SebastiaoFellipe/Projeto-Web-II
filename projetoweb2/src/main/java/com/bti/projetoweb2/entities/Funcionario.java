package com.bti.projetoweb2.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cpf;
    private String cargo;
    private String setor;
    private String tipoContrato;
    private String especialidade;
    private String telefone;
    private String dataAdmissao;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private Usuario usuario;

    @Transient
    private Integer usuarioId;

    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Palestra> palestras = new ArrayList<>();

    
    @ManyToMany(mappedBy = "funcionarios")
    private Set<Visita> visitas = new HashSet<>();

    public Funcionario() {}

    public Funcionario(Integer id, Usuario usuario, String cpf, String cargo, String setor,
                       String tipoContrato, String especialidade, String telefone, String dataAdmissao) {
        this.id = id;
        this.usuario = usuario;
        this.cpf = cpf;
        this.cargo = cargo;
        this.setor = setor;
        this.tipoContrato = tipoContrato;
        this.especialidade = especialidade;
        this.telefone = telefone;
        this.dataAdmissao = dataAdmissao;
    }

    // Getters e Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getSetor() { return setor; }
    public void setSetor(String setor) { this.setor = setor; }

    public String getTipoContrato() { return tipoContrato; }
    public void setTipoContrato(String tipoContrato) { this.tipoContrato = tipoContrato; }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getDataAdmissao() { return dataAdmissao; }
    public void setDataAdmissao(String dataAdmissao) { this.dataAdmissao = dataAdmissao; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Integer getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Integer usuarioId) { this.usuarioId = usuarioId; }

    public List<Palestra> getPalestras() { return palestras; }
    public void setPalestras(List<Palestra> palestras) { this.palestras = palestras; }

    public Set<Visita> getVisitas() { return visitas; }
    public void setVisitas(Set<Visita> visitas) { this.visitas = visitas; }
}
