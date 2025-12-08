package com.bti.projetoweb2.config;

import com.bti.projetoweb2.entities.*;
import com.bti.projetoweb2.repositories.*;
import com.bti.projetoweb2.users.User;
import com.bti.projetoweb2.users.UserRole;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Transactional
    public void run(String... args) throws Exception {
        seedUsers();
        seedHabitats();
        seedFuncionarios();
        seedProfessores();
        seedCandidatos();
        seedEstoque();
        seedAnimais();
        seedVisitas();
        seedPalestras();
        seedAlimentacao();
        seedReabilitacao();
        
        System.out.println("--- Seeding concluído com sucesso ---");
    }

    private void seedUsers() {
        if (userRepository.findByLogin("adm").isEmpty()) {
            User admin = new User("adm", passwordEncoder.encode("123"), UserRole.ADMIN);
            userRepository.save(admin);
        }
    }

    private void seedHabitats() {
        if (habitatRepository.count() < 6) {
            List<Habitat> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                lista.add(new Habitat("Habitat " + i, i % 2 == 0 ? "Aquático" : "Terrestre", 20.0 + i));
            }
            habitatRepository.saveAll(lista);
        }
    }

    private void seedFuncionarios() {
        if (funcionarioRepository.count() < 6) {
            List<Funcionario> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                lista.add(new Funcionario("Funcionario " + i, generateCpf(), "Tratador", TipoVinculo.FIXO));
            }
            funcionarioRepository.saveAll(lista);
        }
    }

    private void seedProfessores() {
        if (professorRepository.count() < 6) {
            List<Professor> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                lista.add(new Professor("Professor " + i, generateCpf(), "Pesquisador", TipoVinculo.TEMPORARIO, "Biologia", NivelAcademico.MESTRADO));
            }
            professorRepository.saveAll(lista);
        }
    }

    private void seedCandidatos() {
        if (candidatoRepository.count() < 6) {
            List<Candidato> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                lista.add(new Candidato("Candidato " + i, generateCpf(), "Zoologia", "email" + i + "@teste.com"));
            }
            candidatoRepository.saveAll(lista);
        }
    }

    private void seedEstoque() {
        if (estoqueRepository.count() < 6) {
            List<Estoque> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                lista.add(new Estoque("COD-" + i, "Produto " + i, 100, "kg", LocalDate.now().plusMonths(6)));
            }
            estoqueRepository.saveAll(lista);
        }
    }

    private void seedAnimais() {
        if (animalRepository.count() < 6) {
            List<Habitat> habitats = habitatRepository.findAll();
            if (habitats.isEmpty()) return;
            
            List<Animal> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                String dataHoje = LocalDate.now().toString();    
                Animal a = new Animal("Animal " + i, "Cientifico", "Familia", "Genero", "Especie",
                        Animal.Classificacao.NAO_AMEACADO, "Onívoro", "Saudável", dataHoje, 2);
                a.setHabitat(habitats.get((i - 1) % habitats.size()));
                lista.add(a);
            }
            animalRepository.saveAll(lista);
        }
    }

    private void seedVisitas() {
        if (visitaRepository.count() < 6) {
            List<Funcionario> funcs = funcionarioRepository.findAll();
            List<Visita> lista = new ArrayList<>();
            
            for (int i = 1; i <= 6; i++) {
                Visita v = new Visita();
                v.setNome("Visita " + i);
                v.setData("2025-12-0" + i);
                v.setHora("08:00");
                v.setInstituicao("Escola " + i);
                v.setQtdAlunos(20);
                v.setProfessorResponsavel("Prof. Visitante");
                v.setTelefone("84 99999-9999");
                if (!funcs.isEmpty()) {
                    Set<Funcionario> equipe = new HashSet<>();
                    equipe.add(funcs.get(i % funcs.size()));
                    v.setFuncionarios(equipe);
                }
                lista.add(v);
            }
            visitaRepository.saveAll(lista);
        }
    }

    private void seedPalestras() {
        if (palestraRepository.count() < 6) {
            List<Funcionario> funcs = funcionarioRepository.findAll();
            List<Palestra> lista = new ArrayList<>();
            for (int i = 1; i <= 6; i++) {
                Palestra p = new Palestra();
                p.setTema("Palestra " + i);
                p.setData("2025-11-20");
                p.setHorario("10:00");
                p.setLocal("Auditório");
                p.setPalestrante("Dr. Teste");
                p.setFormacaoPalestrante("Bio");
                p.setPublicoAlvo("Geral");
                if(!funcs.isEmpty()) p.setFuncionario(funcs.get(i % funcs.size()));
                lista.add(p);
            }
            palestraRepository.saveAll(lista);
        }
    }

    private void seedAlimentacao() {
        if (alimentacaoRepository.count() < 6) {
            List<Funcionario> funcs = funcionarioRepository.findAll();
            List<Animal> anims = animalRepository.findAll();
            List<Estoque> ests = estoqueRepository.findAll();
            if (!funcs.isEmpty() && !anims.isEmpty() && !ests.isEmpty()) {
                List<Alimentacao> lista = new ArrayList<>();
                for (int i = 1; i <= 6; i++) {
                    lista.add(new Alimentacao("Ração " + i, 1.0, LocalDate.now(), "Obs", funcs.get(0), anims.get(0), ests.get(0)));
                }
                alimentacaoRepository.saveAll(lista);
            }
        }
    }

    private void seedReabilitacao() {
        if (reabilitacaoRepository.count() < 6) {
            List<Funcionario> funcs = funcionarioRepository.findAll();
            List<Animal> anims = animalRepository.findAll();
            if (!funcs.isEmpty() && !anims.isEmpty()) {
                List<Reabilitacao> lista = new ArrayList<>();
                for (int i = 1; i <= 6; i++) {
                    lista.add(new Reabilitacao(anims.get(0), "Motivo " + i, "Tratamento", java.sql.Date.valueOf(LocalDate.now()), null, "Em andamento", "Obs", funcs.get(0)));
                }
                reabilitacaoRepository.saveAll(lista);
            }
        }
    }

    private String generateCpf() {
        long randomNum = (long) (Math.random() * 90000000000L) + 10000000000L;
        return String.valueOf(randomNum);
    }
}