package com.algaworks.algafood.core.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** Essa classe configura o Springfox de maneira que ele gere automaticamente a documentação da API baseada
 * nos controladores e endpoints presentes na aplicação */
@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2) // Cria uma instância de Docket configurada para utilizar o tipo de documentação Swagger 2.0
            .select() // Inicia um builder que permite especificar quais controladores e endpoints serão incluídos na documentação.
            .apis(RequestHandlerSelectors.any()) // Configura o Docket para incluir todos os controladores disponíveis na aplicação.
            .build(); // Finaliza a configuração do Docket e retorna a instância pronta para ser gerenciada pelo Spring.
    }
}
