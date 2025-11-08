package com.bti.projetoweb2.config;

import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.entities.TipoVinculo;
import com.bti.projetoweb2.funcionarios.repositories.FuncionarioRepository;
import com.bti.projetoweb2.professor.entities.Professor;
import com.bti.projetoweb2.professor.entities.NivelEnsino;
import com.bti.projetoweb2.professor.repositories.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public DatabaseSeeder(FuncionarioRepository funcionarioRepository, ProfessorRepository professorRepository) {
        this.funcionarioRepository = funcionarioRepository;
        this.professorRepository = professorRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (funcionarioRepository.count() == 0) {
            System.out.println("Populando banco de dados com dados iniciais de funcionários...");

            Funcionario f1 = new Funcionario(
                    "Maria Oliveira",
                    "11122233344",
                    "Bilheteria",
                    TipoVinculo.FIXO
            );

            Funcionario f2 = new Funcionario(
                    "Pedro Rocha",
                    "55566677788",
                    "Manutenção",
                    TipoVinculo.TEMPORARIO
            );

            funcionarioRepository.save(f1);
            funcionarioRepository.save(f2);
        }

        if (professorRepository.count() == 0) {

            Professor p1 = new Professor(
                    "Mariana Filho",
                    "99988877766",
                    "Biologia Marinha",
                    NivelEnsino.SUPERIOR
            );

            Professor p2 = new Professor(
                    "Carlos Mendes",
                    "33322211100",
                    "Epedemiologia",
                    NivelEnsino.DOUTORADO
            );

            professorRepository.save(p1);
            professorRepository.save(p2);
        }
    }
}
