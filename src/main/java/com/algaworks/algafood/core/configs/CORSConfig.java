package com.algaworks.algafood.core.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/** Essa classe configura as políticas de CORS (Cross-Origin Resource Sharing) para a aplicação */
@Configuration
public class CORSConfig implements WebMvcConfigurer {

    @Autowired
    private ApiDeprecationHandlerConfig apiDeprecationHandlerConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        registry
            .addMapping("/**") // Está aplicando as configurações de CORS dessa classe a todas as rotas/endpoints da aplicação
            .allowedOrigins("*") // Está permitindo solicitações de qualquer origem
            .allowedMethods("*") // Está permitindo todos os métodos HTTP (GET, POST, PUT, DELETE, etc.) nas requisições CORS.
            .maxAge(30); //  Tempo (em segundos) que o navegador deve armazenar em cache a resposta da requisição de preflight (verificação prévia que o navegador faz para saber se a requisição real é segura).
    }

//  Adiciona interceptores para pré e pós-processamento de invocações dos controladores da aplicação.
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiDeprecationHandlerConfig);
    }
}
