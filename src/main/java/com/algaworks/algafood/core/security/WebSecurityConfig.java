package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.httpBasic()  // Configura a autenticação básica (Basic Authentication)
            .and()
                .authorizeRequests() //  Define regras de autorização para as requisições
                    .antMatchers("/v1/cidades/**").permitAll() // Permite o acesso sem autenticação para todas as URLs que começam com /v1/cidades/
                    .anyRequest().authenticated() // Requer autenticação para qualquer outra URL que não se encaixe nas regras anteriores.

            .and()
                .sessionManagement() // Permite configurar o gerenciamento de Sessão.
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Cria politica de sessão para ser 'STATELESS',
            // significa que a aplicação não mantém estado entre as requisições.
            // Essa configuração é ideal para APIs RESTful, onde cada requisição é tratada de forma independente.

            .and()
                .csrf() // Proteção contra Cross-Site Request Forgery (CSRF), por padrão é habilitada (enabled)
                    .disable() //  Desabilita a proteção, pois essa proteção geralmente não é necessária em APIs REST que usam autenticação stateless.

        ;


    }
}
