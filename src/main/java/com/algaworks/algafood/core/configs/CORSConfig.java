package com.algaworks.algafood.core.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;

/** Essa classe configura as políticas de CORS (Cross-Origin Resource Sharing) para a aplicação */
@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Autowired
    private ApiDeprecationHandlerConfig apiDeprecationHandlerConfig;

//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//
//        registry
//            .addMapping("/**") // Está aplicando as configurações de CORS dessa classe a todas as rotas/endpoints da aplicação
//            .allowedOrigins("*") // Está permitindo solicitações de qualquer origem
//            .allowedMethods("*") // Está permitindo todos os métodos HTTP (GET, POST, PUT, DELETE, etc.) nas requisições CORS.
//            .maxAge(30); //  Tempo (em segundos) que o navegador deve armazenar em cache a resposta da requisição de preflight (verificação prévia que o navegador faz para saber se a requisição real é segura).
//    }


    @Bean // Configura um FilterRegistrationBean para o filtro CORS
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        CorsConfiguration config = new CorsConfiguration(); // Cria uma nova instância para armazenar as configurações CORS.
        config.setAllowCredentials(false);
        config.setAllowedOrigins(Collections.singletonList("*")); // Permite todas as origens em solicitações CORS.
        config.setAllowedMethods(Collections.singletonList("*")); // Permite todos os métodos HTTP em solicitações CORS.
        config.setAllowedHeaders(Collections.singletonList("*")); // Permite todos os cabeçalhos em solicitações CORS.

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(); // fonte baseada em URL para configurações CORS.
        source.registerCorsConfiguration("/**", config); // Registra a configuração CORS para todas as rotas

        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new CorsFilter(source));

        // Define a prioridade do filtro como a mais alta, garantindo que este filtro seja aplicado antes dos outros.
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

//  Adiciona interceptores para pré e pós-processamento de invocações dos controladores da aplicação.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiDeprecationHandlerConfig);
    }
}
