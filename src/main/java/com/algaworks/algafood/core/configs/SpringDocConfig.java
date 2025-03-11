package com.algaworks.algafood.core.configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
//@SecurityScheme(name = "security_auth",
//    type = SecuritySchemeType.OAUTH2,
//flows = @OAuthFlows(
//    authorizationCode = @OAuthFlow(
//    authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
//    tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
//    scopes = {
//        @OAuthScope(name = "READ", description = "read scope"),
//        @OAuthScope(name = "WRITE", description = "write scope")
//    }
//)))
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

            .addOpenApiCustomiser(openApiCustomiser())

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
                        .url("http://colocar-url-da-documentacao-externa.com"))
                    .tags(Arrays.asList(
                        new Tag().name("Cidades").description("Gerencia as cidades") // Cria tag para ser mapeada com a tag declarada em CidadeController para ser visualizada na documentação.
                    )))
            .build();
    }

    @Bean /** Configura as informações da API versão 2 que serão exibidas na documentação gerada pelo Swagger.*/
    public GroupedOpenApi groupedOpenApiV2() {
        return GroupedOpenApi.builder()
            .group("Alga food API v2")
            .pathsToMatch("/v2/**")

            .addOpenApiCustomiser(openApiCustomiser())

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


    @Bean /* Personaliza as respostas de erro para todas as APIs da aplicação de forma global.*/
    public OpenApiCustomiser openApiCustomiser(){
        return openApi -> {
          openApi.getPaths()
              .values()
              .forEach(pathItem -> pathItem.readOperationsMap()
                  .forEach((httpMethod, operation) -> {

                      ApiResponse erro400 = new ApiResponse().description("Requisição inválida (erro do cliente)");
                      ApiResponse erro404 = new ApiResponse().description("Recurso não encontrado");
                      ApiResponse erro406 = new ApiResponse().description("Recurso não possui representação que poderia ser aceita pelo consumidor");
                      ApiResponse erro415 = new ApiResponse().description("Requisição recusada porque o corpo está em um formato não suportado");
                      ApiResponse erro500 = new ApiResponse().description("Erro inteno no servidor");

                      ApiResponses responses = operation.getResponses();

                      switch (httpMethod){

                          case GET:
                              responses.addApiResponse("404", erro404);
                              responses.addApiResponse("406", erro406);
                              responses.addApiResponse("500", erro500);
                          break;
                          case POST:
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("406", erro406);
                              responses.addApiResponse("415", erro415);
                              responses.addApiResponse("500", erro500);
                          break;
                          case PUT:
                              responses.addApiResponse("404", erro404);
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("406", erro406);
                              responses.addApiResponse("415", erro415);
                              responses.addApiResponse("500", erro500);
                          break;
                          case DELETE:
                              responses.addApiResponse("404", erro404);
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("500", erro500);
                          break;

                      }

                  }));

        };
    }

}
