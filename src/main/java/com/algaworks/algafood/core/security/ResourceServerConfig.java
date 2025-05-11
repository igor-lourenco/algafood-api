package com.algaworks.algafood.core.security;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Configuration
@EnableWebSecurity // Habilita a configuração de segurança da web no Spring Security.
@EnableMethodSecurity(prePostEnabled = true) // Habilita a segurança de métodos em nível global, permitindo a utilização de anotações como @PreAuthorize e @PostAuthorize em seus métodos.
// Isso significa que você pode definir regras de segurança específicas para cada método, como permissões de acesso baseadas em roles ou condições personalizadas.
public class ResourceServerConfig {


    @Bean // Define uma SecurityFilterChain personalizada usando o HttpSecurity
    public SecurityFilterChain resourceServerFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
            .formLogin(Customizer.withDefaults())
            .csrf().disable() // Desativa proteção contra CSRF (Cross-Site Request Forgery) porque o ataque de CSRF geralmente depende de um navegador do usuário e de cookies de autenticação
            .cors() // Habilita suporte a CORS (Cross-Origin Resource Sharing).
            .and()
            .oauth2ResourceServer()
                .jwt() // Configura a aplicação para usar tokens jwt
                    .jwtAuthenticationConverter(jwtAuthenticationConverter()); // nossa imlementacao para ler o token jwt e pegar as informações

//      Para personalizar a página de login implementada no WebMvcSecurityConfig
        return httpSecurity.build();
    }


    // Esse método é responsável por ler as informações customizadas do token JWT
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        log.info(">>> CARREGANDO BEAN PARA LER AS CLAIMS CUSTOMIZADAS DO TOKEN JWT");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();

        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

            Collection<GrantedAuthority> grantedAuthorities = authoritiesConverter.convert(jwt); // lista de scope
            log.info(">> Lista de scopes: {}", grantedAuthorities);

            List<String> authorities = jwt.getClaimAsStringList("authorities"); // lista de authorities
            log.info(">> Lista de authorities: {}", authorities);

            if (authorities == null) {
                return grantedAuthorities;
            }


            // junta a lista de authorities com a lista de scopes em uma só
            grantedAuthorities.addAll(authorities
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList()));

            return grantedAuthorities;
        });

        return converter;
    }
}
