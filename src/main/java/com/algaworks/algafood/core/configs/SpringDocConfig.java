package com.algaworks.algafood.core.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

//    @Bean
//    public OpenAPI openAPI() {
//        return new OpenAPI() /** Configura as informações da API que serão exibidas na documentação gerada pelo Swagger.*/
//            .info(new Info()
//                .title("Alga food API")
//                .version("v1")
//                .description("APIs aberta versão 1 para clientes e restaurantes.</br>"
//                    + "<strong>Essa versão da API está depreciada e deixará de existir a partir de 01/01/2027."
//                    + "Use a versão mais atual da API.</strong>")
//                .contact(new Contact()
//                    .name("Algaworks")
//                    .url("https://www.algaworks.com")
//                    .email("contato@algaworks.com"))
//                .license(new License()
//                    .name("Apache 2.0")
//                    .url("http://springdoc.com")))
//            .externalDocs(new ExternalDocumentation()
//                .description("AlgaWorks")
//                .url("http://colocar-url-da-documentacao-externa.com"));
//    }

    @Bean /** Configura as informações da API versão 1 que serão exibidas na documentação gerada pelo Swagger.*/
    public GroupedOpenApi groupedOpenApiV1() {
        return GroupedOpenApi.builder()
            .group("Alga food API v1")
            .pathsToMatch("/v1/**")
            .addOpenApiCustomiser(openApi ->
                openApi.info(new Info()
                        .title("Alga food API Versão 1")
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
                        .url("http://colocar-url-da-documentacao-externa.com")))
            .build();
    }

    @Bean /** Configura as informações da API versão 2 que serão exibidas na documentação gerada pelo Swagger.*/
    public GroupedOpenApi groupedOpenApiV2() {
        return GroupedOpenApi.builder()
            .group("Alga food API v2")
            .pathsToMatch("/v2/**")
            .addOpenApiCustomiser(openApi ->
                openApi.info(new Info()
                        .title("Alga food API Versão 2")
                        .version("v2")
                        .description("APIs aberta versão 2 para clientes e restaurantes")
                        .contact(new Contact()
                            .name("Algaworks")
                            .url("https://www.algaworks.com")
                            .email("contato@algaworks.com"))
                        .license(new License()
                            .name("Apache 2.0")
                            .url("http://springdoc.com")))
                    .externalDocs(new ExternalDocumentation()
                        .description("AlgaWorks")
                        .url("http://colocar-url-da-documentacao-externa.com")))
            .build();
    }

}
