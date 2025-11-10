package com.bti.projetoweb2.palestras.entities;

import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import jakarta.persistence.*;

@Entity
public class Palestra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String tema;
    private String data;
    private String horario;
    private String local;
    private String palestrante;
    private String formacaoPalestrante;
    private String publicoAlvo;
    
    @Transient
    private Long funcionarioId; 

    

    @ManyToOne
    @JoinColumn(name = "funcionario_id")
    private Funcionario funcionario;

    public Palestra() {}

    public Palestra(int id, String tema, String data, String horario, String local,
                    String palestrante, String formacaoPalestrante, String publicoAlvo) {
        this.id = id;
        this.tema = tema;
        this.data = data;
        this.horario = horario;
        this.local = local;
        this.palestrante = palestrante;
        this.formacaoPalestrante = formacaoPalestrante;
        this.publicoAlvo = publicoAlvo;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTema() { return tema; }
    public void setTema(String tema) { this.tema = tema; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getLocal() { return local; }
    public void setLocal(String local) { this.local = local; }

    public String getPalestrante() { return palestrante; }
    public void setPalestrante(String palestrante) { this.palestrante = palestrante; }

    public String getFormacaoPalestrante() { return formacaoPalestrante; }
    public void setFormacaoPalestrante(String formacaoPalestrante) { this.formacaoPalestrante = formacaoPalestrante; }

    public String getPublicoAlvo() { return publicoAlvo; }
    public void setPublicoAlvo(String publicoAlvo) { this.publicoAlvo = publicoAlvo; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public Long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(Long funcionarioId) { this.funcionarioId = funcionarioId; }
}
