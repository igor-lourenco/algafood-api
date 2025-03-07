package com.algaworks.algafood.core.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI() /** Configura as informações da API que serão exibidas na documentação gerada pelo Swagger.*/
            .info(new Info()
                .title("Alga food API")
                .version("v1")
                .description("APIs aberta versão 1 para clientes e restaurantes.</br>"
                + "<strong>Essa versão da API está depreciada e deixará de existir a partir de 01/01/2027."
                + "Use a versão mais atual da API.</strong>")
                .contact(new Contact()
                    .name("Algaworks")
                    .url("https://www.algaworks.com")
                    .email("contato@algaworks.com"))
                .license(new License()
                    .name("Apache 2.0")
                    .url("http://springdoc.com")))
            .externalDocs(new ExternalDocumentation()
                .description("AlgaWorks")
                .url("http://colocar-url-da-documentacao-externa.com"));
    }

}
