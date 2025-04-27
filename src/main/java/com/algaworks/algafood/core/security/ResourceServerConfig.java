package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Habilita a configuração de segurança da web no Spring Security.
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilita a segurança de métodos em nível global, permitindo a utilização de anotações como @PreAuthorize e @PostAuthorize em seus métodos.
// Isso significa que você pode definir regras de segurança específicas para cada método, como permissões de acesso baseadas em roles ou condições personalizadas.
public class ResourceServerConfig {


    @Bean // Define uma SecurityFilterChain personalizada usando o HttpSecurity
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .authorizeRequests().antMatchers("/oauth2/**").authenticated() // Exige autenticação para todas as requisições da aplicação exceto os endpoints /oauth2/**.
            .and()
            .csrf().disable() // Desativa proteção contra CSRF (Cross-Site Request Forgery) porque o ataque de CSRF geralmente depende de um navegador do usuário e de cookies de autenticação
            .cors() // Habilita suporte a CORS (Cross-Origin Resource Sharing).
            .and()
            .oauth2ResourceServer().jwt(); // Configura a aplicação para usar tokens opacos (que precisam ser validados em um endpoint externo).

        return httpSecurity.formLogin(Customizer.withDefaults()).build();
    }



}
