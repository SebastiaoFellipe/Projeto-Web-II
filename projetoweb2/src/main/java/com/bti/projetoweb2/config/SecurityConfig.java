package com.bti.projetoweb2.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;

// A anotação @EnableWebSecurity pode ser opcional dependendo da versão, 
// mas é bom para garantir que o Spring Security seja ativado.
// Adicione @EnableWebSecurity aqui se sua versão exigir.

@Configuration
public class SecurityConfig { 

    // 1. Define a configuração de CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Permite a origem do seu Front-end
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173")); 
        
        // Permite os métodos, incluindo OPTIONS (essencial para CORS preflight)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); 
        
        configuration.setAllowedHeaders(Arrays.asList("*")); // Permite todos os cabeçalhos
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Aplica a configuração a TODAS as rotas
        source.registerCorsConfiguration("/**", configuration); 
        return source;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Método moderno (lambda) para desativar o CSRF
            .cors(cors -> cors.configurationSource(corsConfigurationSource())) // <-- HABILITA o CORS USANDO O BEAN DEFINIDO ACIMA
            .authorizeHttpRequests(authorize -> authorize // Novo método para configuração de autorização
                .requestMatchers("/**").permitAll() // Permite acesso público a todas as rotas (para testes)
                .anyRequest().authenticated()
            );
        
        return http.build();
    }
}