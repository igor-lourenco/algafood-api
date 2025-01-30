package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Habilita a segurança de métodos em nível global, permitindo a
// utilização de anotações como @PreAuthorize e @PostAuthorize em seus métodos.
// Isso significa que você pode definir regras de segurança específicas para cada método, como permissões de acesso baseadas em roles ou condições personalizadas.
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {

//    Obs: Configuração feita no Authorization Server (projeto algafood-auth)
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication() // Indica que a autenticação será configurada em memória. Os dados dos usuários são definidos diretamente no código e mantidos apenas enquanto a aplicação está em execução.
//                .withUser("igor")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN")
//            .and()
//                .withUser("joao")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN");
//
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
//                .csrf() // Proteção contra Cross-Site Request Forgery (CSRF), por padrão é habilitada (enabled)
//                    .disable().cors() //  Desabilita a proteção, pois essa proteção geralmente não é necessária em APIs REST que usam autenticação stateless.
//
////            .httpBasic()  // Configura a autenticação básica (Basic Authentication)
//
//            --------------------------------------

//            .and()
//                .authorizeRequests() //  Define regras de autorização para as requisições
////                    .antMatchers("/v1/cidades/**").permitAll() // Permite o acesso sem autenticação para todas as URLs que começam com /v1/cidades/
//
//                    .antMatchers(HttpMethod.POST, "/v1/cozinhas/**").hasAuthority("EDITAR_COZINHAS")
//                    .antMatchers(HttpMethod.PUT, "/v1/cozinhas/**").hasAuthority("EDITAR_COZINHAS")
//
//
//
////                  .anyRequest().authenticated() // Requer autenticação para qualquer outra URL que não se encaixe nas regras anteriores.
//                    .anyRequest().denyAll() // Nega todos os acessos que não seja uma das regras acima
//
//          ----------------------------------------
//
//            .and()
//                .sessionManagement() // Permite configurar o gerenciamento de Sessão.
//                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Cria politica de sessão para ser 'STATELESS',
//            // significa que a aplicação não mantém estado entre as requisições.
////          // Essa configuração é ideal para APIs RESTful, onde cada requisição é tratada de forma independente.
////
//          ----------------------------------------
//
//            .and()
//                .oauth2ResourceServer()
////                  .opaqueToken() // É uma string de caracteres aleatórios que não contém nenhuma informação legível ou embutida sobre o usuário, para validar precisa consultar no banco de dados
//                    .jwt() // Contém informações legíveis sobre o usuário e os claims, e pode ser validado diretamente pelo servidor sem necessidade de consulta ao banco de dados
//                    .jwtAuthenticationConverter(jwtAuthenticationConverter())

//           ===== Desse jeito todas as API da aplicação estão liberadas =====
            .csrf().disable()
            .cors()
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(jwtAuthenticationConverter())

        ;
    }


/** Esse método extrai as autoridades definidas no próprio token para o tipo JwtAuthenticationConverter do Spring Security.
    Isso permite que o Spring Security use essas autoridades para decidir o acesso aos recursos na aplicação.*/
    private JwtAuthenticationConverter jwtAuthenticationConverter(){
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();

//      Substitui para usar o nosso converter para pegar os authorities que estão vindo no JWT
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {

//          Extrai do JWT a lista de authorities.
            List<String> authorities = jwt.getClaimAsStringList("authorities");

            if(authorities == null){ // Se não tiver no JWT a propriedade authorities
                authorities = Collections.emptyList();
            }

//          Classe padrão usado pela classe JwtAuthenticationConverter para converter as authorities vindo do JWT para ser utilizadas pelo Spring Security
            var scopesAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

//          Lista de autoridades concedidas a um objeto Authentication. Por padrão ele pega da propriedade 'scopes' do JWT
            Collection<GrantedAuthority> grantedScopes = scopesAuthoritiesConverter.convert(jwt);

//          Converte a lista de authorities vindas do JWT para o tipo SimpleGrantedAuthority do Spring Security
            Collection<GrantedAuthority> grantedAuthorities = authorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

            grantedScopes.addAll(grantedAuthorities); // Adiciona na lista de 'scopes' a lista de 'authorities'

            return grantedScopes;
        });

        return jwtAuthenticationConverter;
    }


//    @Bean // Decodifica o JSON Web Tokens (JWT) usando uma chave secreta simétrica.
//    public JwtDecoder jwtDecoder(){
//
//        // chave secreta em bytes e o algoritmo que vai ser usado para decodificar o token JWT
//        var spec = new SecretKeySpec("89f35f44-a025-4ed0-bb7e-950c033d9563".getBytes(), "HmacSHA256");
//
//        return NimbusJwtDecoder.withSecretKey(spec).build();
//    }


//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }

}
