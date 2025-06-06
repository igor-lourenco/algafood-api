package com.algaworks.algafood.core.security.authorizationServer;

import com.algaworks.algafood.core.properties.AlgafoodSecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.time.Duration;

/**  Essa classe é responsável por configurar o servidor de autorização OAuth2, de como os clientes se autenticam e
  obtêm tokens de acesso.  */
@Configuration
public class AuthorizationServerConfig {

    @Bean // Aplica as configurações de segurança do OAuth2 ao HttpSecurity
    @Order(Ordered.HIGHEST_PRECEDENCE) // Para que as configurações sejam aplicadas com a maior prioridade
    public SecurityFilterChain authFilterChain(HttpSecurity http) throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer();

        authorizationServerConfigurer.authorizationEndpoint(customizer ->
            customizer.consentPage("/oauth2/consent")); // página de consentimento

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

        http
            .formLogin(Customizer.withDefaults())
            .securityMatcher(endpointsMatcher)
            .authorizeHttpRequests(authorizeRequests -> {
                authorizeRequests.anyRequest().authenticated();
            })
            .csrf((csrf) -> {
                csrf.ignoringRequestMatchers(endpointsMatcher);
            })
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer -> {
                httpSecurityExceptionHandlingConfigurer.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));
            })
            .apply(authorizationServerConfigurer);


        return http.build();
    }


    @Bean // Regista cliente OAuth2
    public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder, JdbcOperations jdbcOperations) {

        RegisteredClient algafoodClientCredentialsTokenOpaco = clienteClientCredentialsUsandoTokenOpaco(passwordEncoder);
        RegisteredClient algafoodClientCredentialsTokenJWT = clienteClientCredentialsUsandoTokenJWT(passwordEncoder);
        RegisteredClient algafoodAuthorizationCodeTokenJWT = clienteAuthorizationCodeUsandoTokenJWT(passwordEncoder);
        RegisteredClient algafoodPasswordTokenJWT = clientePasswordUsandoTokenJWT(passwordEncoder); // password flow depreciado


        // armazena em memória
//        return new InMemoryRegisteredClientRepository(
//            Arrays.asList(
//                algafoodClientCredentialsTokenOpaco,
//                algafoodClientCredentialsTokenJWT,
//                algafoodAuthorizationCodeTokenJWT,
//                algafoodPasswordTokenJWT
//            ));

        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcOperations);

//       Obs: Comentado porque na primeira vez que rodou o projeto, as inserções dos clientes já foram feitos, e
//       também foi adicionado as inserções dos clientes via arquivo de migração: resources/db/testdata/afterMigrate.sql
       registeredClientRepository.save(algafoodClientCredentialsTokenOpaco);
       registeredClientRepository.save(algafoodClientCredentialsTokenJWT);
       registeredClientRepository.save(algafoodAuthorizationCodeTokenJWT);

        return registeredClientRepository;

    }

    @Bean // Define as configurações do provedor de identidade, incluindo a URL do emissor (issuer)
    public AuthorizationServerSettings providerSettings(AlgafoodSecurityProperties properties) {
        return AuthorizationServerSettings.builder()
            .issuer(properties.getProviderUrl())
            .build();
    }


    @Bean // Configura serviço de autorização OAuth2 baseado em JDBC para armazenar e gerenciar autorizações de clientes.
    public OAuth2AuthorizationService oAuth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository  registeredClientRepository){
        return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
    }

    private static RegisteredClient clienteClientCredentialsUsandoTokenOpaco(PasswordEncoder passwordEncoder) {
        return RegisteredClient
            .withId("1")
            .clientId("algafood-web-client-credentials-token-opaco")
            .clientSecret(passwordEncoder.encode("web123"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // fluxo client credentials
            .scope("READ")

            .tokenSettings(TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.REFERENCE) // Token Opaco
                .accessTokenTimeToLive(Duration.ofMinutes(30))
                .build())

            .build();
    }

    private static RegisteredClient clienteClientCredentialsUsandoTokenJWT(PasswordEncoder passwordEncoder) {
        return RegisteredClient
            .withId("2")
            .clientId("algafood-web-client-credentials-token-jwt")
            .clientSecret(passwordEncoder.encode("web123"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS) // fluxo client credentials
            .scope("READ")

            .tokenSettings(TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED) // Token JWT
                .accessTokenTimeToLive(Duration.ofMinutes(30))
                .build())

            .build();
    }


    private static RegisteredClient clienteAuthorizationCodeUsandoTokenJWT(PasswordEncoder passwordEncoder) {
        return RegisteredClient
            .withId("3")
            .clientId("algafood-web-authorization-code-token-jwt")
            .clientSecret(passwordEncoder.encode("web123"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
            .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE) // fluxo authorization code
            .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) // implementa o refresh token para esse cliente
            .scope("READ")
            .scope("WRITE")

            .tokenSettings(TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED) // Token JWT
                .accessTokenTimeToLive(Duration.ofMinutes(30))
                .reuseRefreshTokens(false) // refresh token não pode ser reutilizado
                .refreshTokenTimeToLive(Duration.ofDays(1)) // tempo de vida do refresh token
                .build())

            .redirectUri("http://127.0.0.1:8080/authorizated") // Endpoint não existe
            .redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html") // Endpoint do Swagger caso queira testar dentro da documentação Swagger
            .clientSettings(ClientSettings.builder()
                .requireAuthorizationConsent(true) // Obrigatório aparecer a tela de consentimento
                .build())

            .build();
    }


    // O Fluxo Passowrd Flow foi depreciado pelo OAuth 2.1
    private static RegisteredClient clientePasswordUsandoTokenJWT(PasswordEncoder passwordEncoder) {
        return RegisteredClient
            .withId("4")
            .clientId("algafood-web-password-token-jwt")
            .clientSecret(passwordEncoder.encode("web123"))
            .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
            .authorizationGrantType(AuthorizationGrantType.PASSWORD) // fluxo client credentials
            .scope("READ")
            .scope("WRITE")

            .tokenSettings(TokenSettings.builder()
                .accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED) // Token JWT
                .accessTokenTimeToLive(Duration.ofMinutes(30))
                .build())

            .build();
    }

    @Bean  // Configura serviço de autorização OAuth2 baseado em JDBC para armazenar as autorizações de consentimento dos clientes autorizados pelos usuarios.
    public OAuth2AuthorizationConsentService consentService(JdbcOperations jdbcOperations, RegisteredClientRepository  registeredClientRepository){
//        return new InMemoryOAuth2AuthorizationConsentService();
        return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, registeredClientRepository);
    }


//  Configura o nosso próprio bean customizado para consultar as autorizações OAuth2 armazenadas em um banco de dados usando JDBC.
//  Obs: Como o JdbcOperations já existe no contexto como um bean, automaticamente o Spring passa uma instância válida no parâmetro.
    @Bean
    public OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository repository){
        return new JdbcOAuth2AuthorizationQueryService(jdbcOperations, repository);
    }

}

