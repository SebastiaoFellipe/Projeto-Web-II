package com.bti.projetoweb2.config; // Crie o pacote 'config'

import com.bti.projetoweb2.funcionarios.entities.Funcionario;
import com.bti.projetoweb2.funcionarios.entities.TipoVinculo;
import com.bti.projetoweb2.funcionarios.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Classe para popular o banco de dados com dados iniciais //

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public DatabaseSeeder(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        
        if (funcionarioRepository.count() == 0) {
            System.out.println("Populando banco de dados com dados iniciais...");

            Funcionario f1 = new Funcionario(
                    "Maria Oliveira", 
                    "11122233344", 
                    "Analista de Dados Senior", 
                    "Data Science", 
                    TipoVinculo.FIXO, 
                    "Big Data e Python"
            );

            Funcionario f2 = new Funcionario(
                    "Pedro Rocha", 
                    "55566677788", 
                    "Product Owner", 
                    "Produto", 
                    TipoVinculo.TEMPORARIO, 
                    "Metodologias Ágeis"
            );

            funcionarioRepository.save(f1);
            funcionarioRepository.save(f2);

            System.out.println("2 registros de funcionários inseridos com sucesso!");
        }
    }
}