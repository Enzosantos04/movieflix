package com.movieflix.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {
    private final SecurityFilter securityFilter;


    @Bean //siginifica  que o spring vai gerenciar esse metodo automaticamente
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(csrf -> csrf.disable()) // desabilita o CSRF
                // diz pro servidor que não vai usar sessões para autenticação
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // autoriza as requisições HTTP e organiza as rotas
                .authorizeHttpRequests(authorize -> authorize
                        // da a permissao do usuario acessar a rota criacao de usuario
                        //e a rota de login
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/movieflix/auth/login").permitAll()
                        //para qualquer outra requisição, é necessário estar autenticado
                        .anyRequest().authenticated()
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class) // adiciona o filtro de segurança antes do filtro de autenticação padrão
                .build(); // isso retorna o securityFilterChain

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        // AuthenticationManager é responsável por autenticar o usuário
        // ele recebe a configuração de autenticação e retorna um AuthenticationManager
        //permite que o Spring Security use o AuthenticationManager para autenticar usuários automaticamente
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    // esse bean é responsável por codificar as senhas
    // o BCryptPasswordEncoder é uma implementação do PasswordEncoder
    // que utiliza o algoritmo BCrypt para codificar senhas
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
