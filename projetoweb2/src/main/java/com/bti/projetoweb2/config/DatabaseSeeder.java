package com.bti.projetoweb2.config;

import com.bti.projetoweb2.users.User;
import com.bti.projetoweb2.entities.Funcionario;
import com.bti.projetoweb2.entities.NivelAcademico;
import com.bti.projetoweb2.entities.Professor;
import com.bti.projetoweb2.entities.TipoVinculo;
import com.bti.projetoweb2.repositories.FuncionarioRepository;
import com.bti.projetoweb2.repositories.ProfessorRepository;
import com.bti.projetoweb2.repositories.UserRepository; 
import com.bti.projetoweb2.users.UserRole;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;
    private final ProfessorRepository professorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
        FuncionarioRepository funcionarioRepository, 
        ProfessorRepository professorRepository,
        UserRepository userRepository,
        PasswordEncoder passwordEncoder 
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.professorRepository = professorRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        if (funcionarioRepository.count() == 0) {
            System.out.println("Populando banco de dados com dados iniciais de funcionários...");
            
            Funcionario f1 = new Funcionario("Maria Oliveira", "11122233344", "Bilheteria", TipoVinculo.FIXO);
            Funcionario f2 = new Funcionario("Pedro Rocha", "55566677788", "Manutenção", TipoVinculo.TEMPORARIO);
            Funcionario f3 = new Funcionario("João Silva", "44455566677", "TI", TipoVinculo.FIXO);

            Professor p1 = new Professor("Mariana Filho", "99988877766", "Pesquisador", TipoVinculo.TEMPORARIO, "Biologia Molecular", NivelAcademico.MESTRADO);
            Professor p2 = new Professor("Carlos Mendes", "33322211100", "Pesquisador", TipoVinculo.FIXO, "Astrofísica", NivelAcademico.DOUTORADO);

            funcionarioRepository.saveAll(java.util.List.of(f1, f2, f3));
            professorRepository.saveAll(java.util.List.of(p1, p2));
        }

        if (userRepository.findByLogin("adm").isEmpty()) {
            System.out.println("Criando usuário administrador padrão...");

            String encodedPassword = passwordEncoder.encode("123"); 
            
            User admin = new User();
            admin.setLogin("adm");
            admin.setPassword(encodedPassword);
            admin.setRole(UserRole.ADMIN);
            
            userRepository.save(admin); 
            
            System.out.println("Usuário ADMIN criado com sucesso: Login: 'adm' / Senha: '123'");
        }
    }
}