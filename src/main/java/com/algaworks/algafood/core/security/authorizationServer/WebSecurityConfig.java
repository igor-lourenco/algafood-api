package com.algaworks.algafood.core.security.authorizationServer;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

/**  Essa classe configura a segurança da aplicação web em geral, definindo usuários, codificando senhas
  com BCrypt e fornecendo um gerenciador de autenticação.  */
@Configuration
@EnableWebSecurity  //  Habilita a configuração de segurança da web no Spring Security.
public class WebSecurityConfig {//extends WebSecurityConfigurerAdapter {
    // TODO: Movido para a classe ResourceServerConfig

//    @Bean
//    @Override //  Define um bean de AuthenticationManager que usa a implementação padrão fornecida pela superclasse.
//    protected AuthenticationManager authenticationManager() throws Exception{
//        return super.authenticationManager();
//    }

//    Implementação para usar os dados do usuário em memória. Obs: foi implementado para pegar usuario final no banco de dados(UsuarioModel)
//    @Override // Configura a autenticação do usuário final (Resource Owner)
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

//    Implementação para usar os dados do usuário em memória
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return super.userDetailsService();
//    }
}
