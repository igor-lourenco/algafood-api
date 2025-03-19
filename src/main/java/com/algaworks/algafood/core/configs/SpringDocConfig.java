package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.api.inputs.EstadoIdInput;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.*;
import com.algaworks.algafood.swaggerOpenApi.models.CidadesCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.FormasPagamentoCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.GruposCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.*;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

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
                        new Tag().name("Cidades").description("Gerencia as cidades"), // Cria tag para ser mapeada com a tag declarada em CidadeControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Cozinhas").description("Gerencia as Cozinhas"), // Cria tag para ser mapeada com a tag declarada em CozinhaControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Formas de pagamento").description("Gerencia as Formas de Pagamento"), // Cria tag para ser mapeada com a tag declarada em FormaPagamentoControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Grupos").description("Gerencia os grupos") // Cria tag para ser mapeada com a tag declarada em GrupoControllerOpenApi para ser visualizada na documentação.
                    ))
                    .components(new Components().schemas(gerarSchemas()))
            ).build();
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
          openApi.getPaths() // Retorna um mapa dos caminhos da API (URLs) da aplicação.
              .values() // Converte o Paths retornado para Collection
              .forEach(pathItem -> pathItem.readOperationsMap() // retorna um mapa das operações HTTP associadas a um PathItem. Cada chave no mapa é um método HTTP (HttpMethod)
                  .forEach((httpMethod, operation) -> {

                      ApiResponse erro400 = new ApiResponse().description("Requisição inválida (erro do cliente)")
                          .content(new Content().addMediaType("application/json", new MediaType()
                              .schema(new Schema<>().properties((ModelConverters.getInstance().readAllAsResolvedSchema(StandardErrorBadRequest.class).schema.getProperties())))));

                      ApiResponse erro404 = new ApiResponse().description("Recurso não encontrado")
                          .content(new Content().addMediaType("application/json", new MediaType()
                              .schema(new Schema<>().properties((ModelConverters.getInstance().readAllAsResolvedSchema(StandardErrorNotFound.class).schema.getProperties())))));

                      ApiResponse erro406 = new ApiResponse().description("Recurso não possui representação que poderia ser aceita pelo consumidor, ajuste o header Accept");

                      ApiResponse erro415 = new ApiResponse().description("Requisição recusada porque o corpo está em um formato não suportado, ajuste o header Content-Type")
                          .content(new Content().addMediaType("application/json", new MediaType()
                              .schema(new Schema<>().properties((ModelConverters.getInstance().readAllAsResolvedSchema(StandardErrorMediaTypeNotSupported.class).schema.getProperties())))));

                      ApiResponse erro500 = new ApiResponse().description("Erro interno no servidor")
                          .content(new Content().addMediaType("application/json", new MediaType()
                              .schema(new Schema<>().properties(ModelConverters.getInstance().readAllAsResolvedSchema(StandardInternalServerError.class).schema.getProperties()))));

                      ApiResponses responses = operation.getResponses();

                      switch (httpMethod){

                          case GET:
                              responses.addApiResponse("406", erro406); // Accept
                              responses.addApiResponse("500", erro500);
                          break;
                          case POST:
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("406", erro406);
                              responses.addApiResponse("415", erro415); // Content-Type
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


    private Map<String, Schema> gerarSchemas() {
        Map<String, Schema> schemaMap = new TreeMap<>(Comparator.naturalOrder()); // Ordena os Schemas por nome para aparecer na documentação


        Map<String, Schema> errorNotFound = ModelConverters.getInstance().read(StandardErrorNotFound.class);
        Map<String, Schema> errorGone = ModelConverters.getInstance().read(StandardErrorGone.class);
        Map<String, Schema> errorBadRequest = ModelConverters.getInstance().read(StandardErrorBadRequest.class);
        Map<String, Schema> errorMediaTypeNotSupported = ModelConverters.getInstance().read(StandardErrorMediaTypeNotSupported.class);
        Map<String, Schema> errorInternalServer = ModelConverters.getInstance().read(StandardInternalServerError.class);


        Map<String, Schema> cidadeHateoasOpenApi = ModelConverters.getInstance().read(CidadeHateoasOpenApi.class);
        Map<String, Schema> cidadesCollectionModelOpenApi = ModelConverters.getInstance().read(CidadesCollectionModelOpenApi.class);
        Map<String, Schema> cidadesEmbeddedModelOpenApi = ModelConverters.getInstance().read(CidadesEmbeddedModelOpenApi.class);
        Map<String, Schema> estadoHateoasOpenApi = ModelConverters.getInstance().read(EstadoHateoasOpenApi.class);
        Map<String, Schema> cidadeInput = ModelConverters.getInstance().read(CidadeInput.class);
        Map<String, Schema> estadoIdInput = ModelConverters.getInstance().read(EstadoIdInput.class);

        Map<String, Schema> gruposCollectionModelOpenApi = ModelConverters.getInstance().read(GruposCollectionModelOpenApi.class);
        Map<String, Schema> gruposEmbeddedModelOpenApi = ModelConverters.getInstance().read(GruposEmbeddedModelOpenApi.class);
        Map<String, Schema> grupoHateoasOpenApi = ModelConverters.getInstance().read(GrupoHateoasOpenApi.class);
        Map<String, Schema> grupoInput = ModelConverters.getInstance().read(GrupoInput.class);

        Map<String, Schema> formasPagamentoCollectionModelOpenApi = ModelConverters.getInstance().read(FormasPagamentoCollectionModelOpenApi.class);
        Map<String, Schema> formasPagamentoEmbeddedModelOpenApi = ModelConverters.getInstance().read(FormasPagamentoEmbeddedModelOpenApi.class);
        Map<String, Schema> formaPagamentoHateoasOpenApi = ModelConverters.getInstance().read(FormaPagamentoHateoasOpenApi.class);
        Map<String, Schema> formaPagamentoInput = ModelConverters.getInstance().read(FormaPagamentoInput.class);

        Map<String, Schema> cozinhaHateoasOpenApi = ModelConverters.getInstance().read(CozinhaHateoasOpenApi.class);



        Map<String, Schema> links = ModelConverters.getInstance().read(LinksModelOpenApi.class);
        Map<String, Schema> rel = ModelConverters.getInstance().read(LinksModelOpenApi.LinkModel.class);


        schemaMap.putAll(cidadesCollectionModelOpenApi);
        schemaMap.putAll(cidadesEmbeddedModelOpenApi);
        schemaMap.putAll(cidadeHateoasOpenApi);
        schemaMap.putAll(estadoHateoasOpenApi);
        schemaMap.putAll(cidadeInput);
        schemaMap.putAll(estadoIdInput);


        schemaMap.putAll(gruposCollectionModelOpenApi);
        schemaMap.putAll(gruposEmbeddedModelOpenApi);
        schemaMap.putAll(grupoHateoasOpenApi);
        schemaMap.putAll(grupoInput);
        schemaMap.putAll(formaPagamentoInput);

        schemaMap.putAll(cozinhaHateoasOpenApi);


        schemaMap.putAll(formasPagamentoCollectionModelOpenApi);
        schemaMap.putAll(formasPagamentoEmbeddedModelOpenApi);
        schemaMap.putAll(formaPagamentoHateoasOpenApi);


        schemaMap.putAll(links);
        schemaMap.putAll(rel);

        schemaMap.putAll(errorNotFound);
        schemaMap.putAll(errorGone);
        schemaMap.putAll(errorBadRequest);
        schemaMap.putAll(errorMediaTypeNotSupported);
        schemaMap.putAll(errorInternalServer);

        return schemaMap;

    }

}
