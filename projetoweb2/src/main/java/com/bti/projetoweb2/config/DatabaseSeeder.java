package com.bti.projetoweb2.config;

import com.bti.projetoweb2.entities.*;
import com.bti.projetoweb2.repositories.*;
import com.bti.projetoweb2.users.User;
import com.bti.projetoweb2.users.UserRole;
// CORREÇÃO: Importar o repositório correto, não a interface interna de User
import com.bti.projetoweb2.repositories.UserRepository; 

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final FuncionarioRepository funcionarioRepository;
    private final ProfessorRepository professorRepository;
    private final HabitatRepository habitatRepository;
    private final AnimalRepository animalRepository;
    private final VisitaRepository visitaRepository;
    private final PalestraRepository palestraRepository;
    private final CandidatoRepository candidatoRepository;
    private final EstoqueRepository estoqueRepository;
    private final AlimentacaoRepository alimentacaoRepository;
    private final ReabilitacaoRepository reabilitacaoRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseSeeder(
            FuncionarioRepository funcionarioRepository,
            ProfessorRepository professorRepository,
            HabitatRepository habitatRepository,
            AnimalRepository animalRepository,
            VisitaRepository visitaRepository,
            PalestraRepository palestraRepository,
            CandidatoRepository candidatoRepository,
            EstoqueRepository estoqueRepository,
            AlimentacaoRepository alimentacaoRepository,
            ReabilitacaoRepository reabilitacaoRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.funcionarioRepository = funcionarioRepository;
        this.professorRepository = professorRepository;
        this.habitatRepository = habitatRepository;
        this.animalRepository = animalRepository;
        this.visitaRepository = visitaRepository;
        this.palestraRepository = palestraRepository;
        this.candidatoRepository = candidatoRepository;
        this.estoqueRepository = estoqueRepository;
        this.alimentacaoRepository = alimentacaoRepository;
        this.reabilitacaoRepository = reabilitacaoRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        seedUsers();
        seedHabitats();
        seedFuncionarios();
        seedProfessores();
        seedCandidatos();
        seedEstoque();
        seedAnimais();
        seedPalestras();
        seedVisitas();
        
        System.out.println("--- Seeding concluído: Garantido mínimo de 6 registros por entidade ---");
    }

    private void seedUsers() {
        if (userRepository.findByLogin("adm").isEmpty()) {
            User admin = new User("adm", passwordEncoder.encode("123"), UserRole.ADMIN);
            userRepository.save(admin);
            System.out.println("Usuário ADMIN criado.");
        }
    }

    private void seedHabitats() {
        long count = habitatRepository.count();
        if (count < 6) {
            List<Habitat> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                String tipo = (i % 2 == 0) ? "Aquático" : "Terrestre";
                lista.add(new Habitat("Habitat Auto " + generateSuffix(), tipo, 20.0 + i));
            }
            habitatRepository.saveAll(lista);
        }
    }

    private void seedFuncionarios() {
        long count = funcionarioRepository.count();
        if (count < 6) {
            List<Funcionario> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                lista.add(new Funcionario("Funcionario Auto " + generateSuffix(), generateCpf(), "Cargo Geral", TipoVinculo.FIXO));
            }
            funcionarioRepository.saveAll(lista);
        }
    }

    private void seedProfessores() {
        long count = professorRepository.count();
        if (count < 6) {
            List<Professor> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                lista.add(new Professor("Professor Auto " + generateSuffix(), generateCpf(), "Pesquisador", TipoVinculo.TEMPORARIO, "Biologia " + i, NivelAcademico.DOUTORADO));
            }
            professorRepository.saveAll(lista);
        }
    }

    private void seedCandidatos() {
        long count = candidatoRepository.count();
        if (count < 6) {
            List<Candidato> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                lista.add(new Candidato("Candidato Auto " + generateSuffix(), generateCpf(), "Zoologia", "email" + generateSuffix() + "@teste.com"));
            }
            candidatoRepository.saveAll(lista);
        }
    }

    private void seedEstoque() {
        long count = estoqueRepository.count();
        if (count < 6) {
            List<Estoque> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                lista.add(new Estoque("COD-" + generateSuffix(), "Produto Auto " + i, 50, "un", LocalDate.now().plusMonths(6)));
            }
            estoqueRepository.saveAll(lista);
        }
    }

    private void seedAnimais() {
        long count = animalRepository.count();
        if (count < 6) {
            List<Habitat> habitats = habitatRepository.findAll();
            if (habitats.isEmpty()) return;

            List<Animal> lista = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i <= (6 - count); i++) {
                Animal a = new Animal("Animal Auto " + generateSuffix(), "Cientifico", "Familia", "Genero", "Especie",
                        Animal.Classificacao.NAO_AMEACADO, "Onívoro", "Saudável", java.sql.Date.valueOf(LocalDate.now()), 2);
                a.setHabitat(habitats.get(rand.nextInt(habitats.size())));
                lista.add(a);
            }
            animalRepository.saveAll(lista);
        }
    }

    private void seedPalestras() {
        long count = palestraRepository.count();
        if (count < 6) {
            List<Funcionario> funcs = funcionarioRepository.findAll();
            
            List<Palestra> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                Palestra p = new Palestra();
                p.setTema("Palestra Auto " + generateSuffix());
                p.setData("2025-12-01");
                p.setHorario("10:00");
                p.setLocal("Auditório B");
                p.setPalestrante("Dr. Seeder");
                p.setFormacaoPalestrante("Biólogo");
                p.setPublicoAlvo("Estudantes");
                if (!funcs.isEmpty()) p.setFuncionario(funcs.get(0));
                lista.add(p);
            }
            palestraRepository.saveAll(lista);
        }
    }

    private void seedVisitas() {
        long count = visitaRepository.count();
        if (count < 6) {
            List<Visita> lista = new ArrayList<>();
            for (int i = 0; i <= (6 - count); i++) {
                Visita v = new Visita();
                v.setNome("Visita Auto " + generateSuffix());
                v.setData("2025-11-15");
                v.setHora("14:00");
                v.setInstituicao("Escola Estadual");
                v.setQtdAlunos(15);
                v.setTipoInstituicao("Pública");
                v.setProfessorResponsavel("Prof. Visitante");
                v.setTelefone("84 99999-9999");
                lista.add(v);
            }
            visitaRepository.saveAll(lista);
        }
    }

    private String generateSuffix() {
        return String.valueOf(System.nanoTime() % 100000);
    }

    private String generateCpf() {
        long randomNum = (long) (Math.random() * 90000000000L) + 10000000000L;
        return String.valueOf(randomNum);
    }
}