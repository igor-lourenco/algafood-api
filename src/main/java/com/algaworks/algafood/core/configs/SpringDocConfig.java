package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.swaggerOpenApi.exceptions.*;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.tags.Tag;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;


@Configuration
//@SecurityScheme(
//    name = "security_auth",    // Password Flow
//    type = SecuritySchemeType.OAUTH2,
//    flows = @OAuthFlows(
//        password = @OAuthFlow(
//            tokenUrl = "${springdoc.oAuthFlow.authorizationUrl}",
//            scopes = {
//                @OAuthScope(name = "READ", description = "read scope"),
//                @OAuthScope(name = "WRITE", description = "write scope")
//            }
//        )
//    )
//)

@SecurityScheme(name = "security_auth", // AuthorizationCode Flow
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        authorizationCode = @OAuthFlow(
            authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
            tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
            scopes = {
                @OAuthScope(name = "READ", description = "read scope"),
                @OAuthScope(name = "WRITE", description = "write scope")
            }
)))
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

            .addOpenApiCustomizer(openApiCustomiserRespostaDeErroParaAsApis())

            .addOpenApiCustomizer(SpringDocConfig::openAPICustomizaOrdemDosSchemas)

            .addOpenApiCustomizer(openApi ->
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
                        new Tag().name("Root entry point").description("Ponto incial(acesso as APIs) da aplicação"), // Cria tag para ser mapeada com a tag declarada em UsuarioControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Cidades").description("Gerencia as cidades"), // Cria tag para ser mapeada com a tag declarada em CidadeControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Cozinhas").description("Gerencia as cozinhas"), // Cria tag para ser mapeada com a tag declarada em CozinhaControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Estados").description("Gerencia os estados"), // Cria tag para ser mapeada com a tag declarada em EstadoControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Estatísticas").description("Estatísticas de AlgaFood"), // Cria tag para ser mapeada com a tag declarada em UsuarioControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Grupos").description("Gerencia os grupos"), // Cria tag para ser mapeada com a tag declarada em GrupoControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Pedidos").description("Gerencia os Pedidos"), // Cria tag para ser mapeada com a tag declarada em PedidoControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Permissões").description("Gerencia as permissões"), // Cria tag para ser mapeada com a tag declarada em UsuarioControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Produtos").description("Gerencia os produtos"), // Cria tag para ser mapeada com a tag declarada em PedidoControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Restaurantes").description("Gerencia os restaurantes"), // Cria tag para ser mapeada com a tag declarada em RestauranteControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Usuarios").description("Gerencia os usuários"), // Cria tag para ser mapeada com a tag declarada em UsuarioControllerOpenApi para ser visualizada na documentação.
                        new Tag().name("Formas de pagamento").description("Gerencia as formas de pagamento") // Cria tag para ser mapeada com a tag declarada em FormaPagamentoControllerOpenApi para ser visualizada na documentação.
                    ))
//                    .components(new Components().schemas(geraSchemas()))

            ).build();
    }

    @Bean /** Configura as informações da API versão 2 que serão exibidas na documentação gerada pelo Swagger.*/
    public GroupedOpenApi groupedOpenApiV2() {
        return GroupedOpenApi.builder()
            .group("Alga food API v2")
            .pathsToMatch("/v2/**")

            .addOpenApiCustomizer(openApiCustomiserRespostaDeErroParaAsApis())

            .addOpenApiCustomizer(SpringDocConfig::openAPICustomizaOrdemDosSchemas)

            .addOpenApiCustomizer(openApi ->
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
                        .url("http://colocar-url-da-documentacao-externa.com"))
                    .tags(Arrays.asList(
                        new Tag().name("Root entry point").description("Ponto incial(acesso as APIs v2) da aplicação"), // Cria tag para ser mapeada com a tag declarada em CozinhaControllerOpenApiV2 para ser visualizada na documentação.
                        new Tag().name("Cozinhas").description("Gerencia as cozihas"), // Cria tag para ser mapeada com a tag declarada em CozinhaControllerOpenApiV2 para ser visualizada na documentação.
                        new Tag().name("Cidades").description("Gerencia as cidades") // Cria tag para ser mapeada com a tag declarada em CidadeControllerOpenApiV2 para ser visualizada na documentação.

                    ))
            )
            .build();
    }


//  Esse método pega os Schemas da aplicação e ordena na ordem alfabética
    private static void openAPICustomizaOrdemDosSchemas(OpenAPI openApi) {

        Components components = openApi.getComponents(); // Recupera os componentes existentes ou cria novos se não existirem
        if (components == null) {
            components = new Components();
            openApi.components(components);
        }

        Map<String, Schema> schemas = components.getSchemas(); // Recupera os schemas existentes
        if (schemas != null) {

            Map<String, Schema> sortedSchemas = new TreeMap<>(Comparator.naturalOrder()); // Ordena os schemas em ordem alfabética
            sortedSchemas.putAll(schemas);

            components.schemas(sortedSchemas); // Substitui os schemas existentes pelo mapa ordenado
        }
    }

    @Bean /* Personaliza as respostas de erro para todas as APIs da aplicação de forma global.*/
    public OpenApiCustomizer openApiCustomiserRespostaDeErroParaAsApis(){
        return openApi -> {
          openApi.getPaths() // Retorna um mapa dos caminhos da API (URLs) da aplicação.
              .values() // Converte o Paths retornado para Collection
              .forEach(pathItem -> pathItem.readOperationsMap() // retorna um mapa das operações HTTP associadas a um PathItem. Cada chave no mapa é um método HTTP (HttpMethod)
                  .forEach((httpMethod, operation) -> {

                      ApiResponse erro400 = new ApiResponse().description("Requisição inválida (erro do cliente)")
                          .content(new Content().addMediaType("application/json", new MediaType()
                              .schema(new Schema<>().properties((ModelConverters.getInstance().readAllAsResolvedSchema(StandardErrorBadRequest.class).schema.getProperties())))));

                      ApiResponse erro403 = new ApiResponse().description("Aceso negado")
                          .content(new Content().addMediaType("application/json", new MediaType()
                              .schema(new Schema<>().properties((ModelConverters.getInstance().readAllAsResolvedSchema(StandardErrorForbidden.class).schema.getProperties())))));

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
                              responses.addApiResponse("403", erro403);
                              responses.addApiResponse("500", erro500);
                          break;
                          case POST:
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("403", erro403);
                              responses.addApiResponse("406", erro406);
                              responses.addApiResponse("415", erro415); // Content-Type
                              responses.addApiResponse("500", erro500);
                          break;
                          case PUT:
                              responses.addApiResponse("403", erro403);
                              responses.addApiResponse("404", erro404);
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("406", erro406);
                              responses.addApiResponse("415", erro415);
                              responses.addApiResponse("500", erro500);
                          break;
                          case DELETE:
                              responses.addApiResponse("403", erro403);
                              responses.addApiResponse("404", erro404);
                              responses.addApiResponse("400", erro400);
                              responses.addApiResponse("500", erro500);
                          break;

                      }

                  }));

        };
    }


//    private Map<String, Schema> geraSchemas() {
//        Map<String, Schema> schemaMap = new TreeMap<>(Comparator.naturalOrder()); // Ordena os Schemas por nome para aparecer na documentação
//
//
//        Map<String, Schema> errorNotFound = ModelConverters.getInstance().read(StandardErrorNotFound.class);
//        Map<String, Schema> errorGone = ModelConverters.getInstance().read(StandardErrorGone.class);
//        Map<String, Schema> errorBadRequest = ModelConverters.getInstance().read(StandardErrorBadRequest.class);
//        Map<String, Schema> errorMediaTypeNotSupported = ModelConverters.getInstance().read(StandardErrorMediaTypeNotSupported.class);
//        Map<String, Schema> errorInternalServer = ModelConverters.getInstance().read(StandardInternalServerError.class);
//
//
//        Map<String, Schema> cidadeHateoasOpenApi = ModelConverters.getInstance().read(CidadeHateoasOpenApi.class);
//        Map<String, Schema> cidadesCollectionModelOpenApi = ModelConverters.getInstance().read(CidadesCollectionModelOpenApi.class);
//        Map<String, Schema> cidadesEmbeddedModelOpenApi = ModelConverters.getInstance().read(CidadesEmbeddedModelOpenApi.class);
//        Map<String, Schema> estadoHateoasOpenApi = ModelConverters.getInstance().read(EstadoHateoasOpenApi.class);
//        Map<String, Schema> cidadeInput = ModelConverters.getInstance().read(CidadeInput.class);
//        Map<String, Schema> estadoIdInput = ModelConverters.getInstance().read(EstadoIdInput.class);
//
//        Map<String, Schema> gruposCollectionModelOpenApi = ModelConverters.getInstance().read(GruposCollectionModelOpenApi.class);
//        Map<String, Schema> gruposEmbeddedModelOpenApi = ModelConverters.getInstance().read(GruposEmbeddedModelOpenApi.class);
//        Map<String, Schema> grupoHateoasOpenApi = ModelConverters.getInstance().read(GrupoHateoasOpenApi.class);
//        Map<String, Schema> grupoInput = ModelConverters.getInstance().read(GrupoInput.class);
//
//        Map<String, Schema> formasPagamentoCollectionModelOpenApi = ModelConverters.getInstance().read(FormasPagamentoCollectionModelOpenApi.class);
//        Map<String, Schema> formasPagamentoEmbeddedModelOpenApi = ModelConverters.getInstance().read(FormasPagamentoEmbeddedModelOpenApi.class);
//        Map<String, Schema> formaPagamentoHateoasOpenApi = ModelConverters.getInstance().read(FormaPagamentoHateoasOpenApi.class);
//        Map<String, Schema> formaPagamentoInput = ModelConverters.getInstance().read(FormaPagamentoInput.class);
//
//        Map<String, Schema> cozinhasPagedListModelOpenApi = ModelConverters.getInstance().read(CozinhasPagedListModelOpenApi.class);
//        Map<String, Schema> cozinhasCollectionModelOpenApiPageable = ModelConverters.getInstance().read(CozinhasPagedListModelOpenApi.Pageable.class);
//        Map<String, Schema> cozinhasCollectionModelOpenApiPageableSort = ModelConverters.getInstance().read(CozinhasPagedListModelOpenApi.Pageable.Sort.class);
//
//        Map<String, Schema> cozinhasPagedCollectionModelOpenApi = ModelConverters.getInstance().read(CozinhasPagedCollectionModelOpenApi.class);
//        Map<String, Schema> cozinhasPagedCollectionModelOpenApi2 = ModelConverters.getInstance().read(CozinhasPagedCollectionModelOpenApi.CozinhaPagedCollection.class);
//
//        Map<String, Schema> cozinhasCollectionModelOpenApi = ModelConverters.getInstance().read(CozinhasCollectionModelOpenApi.class);
//        Map<String, Schema> cozinhasEmbeddedModelOpenApi = ModelConverters.getInstance().read(CozinhasEmbeddedModelOpenApi.class);
//        Map<String, Schema> cozinhaHateoasOpenApi = ModelConverters.getInstance().read(CozinhaHateoasOpenApi.class);
//        Map<String, Schema> cozinhaInput = ModelConverters.getInstance().read(CozinhaInput.class);
//
//
//        Map<String, Schema> pedidosCollectionModelOpenApi = ModelConverters.getInstance().read(PedidosCollectionModelOpenApi.class);
//        Map<String, Schema> pedidosEmbeddedModelOpenApi = ModelConverters.getInstance().read(PedidosEmbeddedModelOpenApi.class);
//        Map<String, Schema> pedidoResumoHateoasOpenApi = ModelConverters.getInstance().read(PedidoResumoHateoasOpenApi.class);
//        Map<String, Schema> enderecoHateoasOpenApi = ModelConverters.getInstance().read(EnderecoHateoasOpenApi.class);
//        Map<String, Schema> cidadeResumoHateoasOpenApi = ModelConverters.getInstance().read(CidadeResumoHateoasOpenApi.class);
//        Map<String, Schema> restauranteNomeHateoasOpenApi = ModelConverters.getInstance().read(RestauranteNomeHateoasOpenApi.class);
//        Map<String, Schema> usuarioHateoasOpenApi = ModelConverters.getInstance().read(UsuarioHateoasOpenApi.class);
//        Map<String, Schema> itemPedidoHateoasOpenApi = ModelConverters.getInstance().read(ItemPedidoHateoasOpenApi.class);
//
//        Map<String, Schema> pedidoHateoasOpenApi = ModelConverters.getInstance().read(PedidoHateoasOpenApi.class);
//        Map<String, Schema> pedidoInput = ModelConverters.getInstance().read(PedidoInput.class);
//        Map<String, Schema> enderecoInput = ModelConverters.getInstance().read(EnderecoInput.class);
//        Map<String, Schema> itemPedidoInput = ModelConverters.getInstance().read(ItemPedidoInput.class);
//
//        Map<String, Schema> pedidoResumoFilterOpenApi = ModelConverters.getInstance().read(PedidoResumoFilterOpenApi.class);
//
//        Map<String, Schema> pedidosPagedCollectionModelOpenApi = ModelConverters.getInstance().read(PedidosPagedCollectionModelOpenApi.class);
//        Map<String, Schema> pedidosPagedCollectionModelOpenApiPedidoPagedCollection = ModelConverters.getInstance().read(PedidosPagedCollectionModelOpenApi.PedidoPagedCollection.class);
//
//        Map<String, Schema> restaurantesCollectionModelOpenApi = ModelConverters.getInstance().read(RestaurantesCollectionModelOpenApi.class);
//        Map<String, Schema> restaurantesEmbeddedModelOpenApi = ModelConverters.getInstance().read(RestaurantesEmbeddedModelOpenApi.class);
//        Map<String, Schema> restauranteHateoasOpenApi = ModelConverters.getInstance().read(RestauranteHateoasOpenApi.class);
//        Map<String, Schema> restauranteInput = ModelConverters.getInstance().read(RestauranteInput.class);
//        Map<String, Schema> restauranteParcialModelOpenApi = ModelConverters.getInstance().read(RestauranteParcialModelOpenApi.class);
//        Map<String, Schema> restauranteViewDTO = ModelConverters.getInstance().read(RestauranteViewDTO.class);
//        Map<String, Schema> cozinhaViewDTO = ModelConverters.getInstance().read(CozinhaViewDTO.class);
//        Map<String, Schema> enderecoDTO = ModelConverters.getInstance().read(EnderecoDTO.class);
//        Map<String, Schema> restauranteNomeOpenApi = ModelConverters.getInstance().read(RestauranteNomeOpenApi.class);
//
//        Map<String, Schema> estadosCollectionModelOpenApi = ModelConverters.getInstance().read(EstadosCollectionModelOpenApi.class);
//        Map<String, Schema> estadosEmbeddedModelOpenApi = ModelConverters.getInstance().read(EstadosEmbeddedModelOpenApi.class);
//        Map<String, Schema> estadoInput = ModelConverters.getInstance().read(EstadoInput.class);
//
//        Map<String, Schema> restauranteResponsaveisCollectionModelOpenApi = ModelConverters.getInstance().read(RestauranteResponsaveisCollectionModelOpenApi.class);
//        Map<String, Schema> restauranteResponsaveisEmbeddedModelOpenApi = ModelConverters.getInstance().read(RestauranteResponsaveisEmbeddedModelOpenApi.class);
//        Map<String, Schema> restauranteResponsaveisHateoasOpenApi = ModelConverters.getInstance().read(RestauranteResponsaveisHateoasOpenApi.class);
//
//        Map<String, Schema> restauranteProdutosCollectionModelOpenApi = ModelConverters.getInstance().read(RestauranteProdutosCollectionModelOpenApi.class);
//        Map<String, Schema> restauranteProdutosEmbeddedModelOpenApi = ModelConverters.getInstance().read(RestauranteProdutosEmbeddedModelOpenApi.class);
//        Map<String, Schema> restauranteProdutoHateoasOpenApi = ModelConverters.getInstance().read(RestauranteProdutoHateoasOpenApi.class);
//        Map<String, Schema> produtoInput = ModelConverters.getInstance().read(ProdutoInput.class);
//
//        Map<String, Schema> gruposPermissaoCollectionModelOpenApi = ModelConverters.getInstance().read(GruposPermissaoCollectionModelOpenApi.class);
//        Map<String, Schema> gruposPermissaoEmbeddedModelOpenApi = ModelConverters.getInstance().read(GruposPermissaoEmbeddedModelOpenApi.class);
//        Map<String, Schema> grupoPermissaoHateoasOpenApi = ModelConverters.getInstance().read(GrupoPermissaoHateoasOpenApi.class);
//
//        Map<String, Schema> restauranteProdutoFotoHateoasOpenApi = ModelConverters.getInstance().read(RestauranteProdutoFotoHateoasOpenApi.class);
//        Map<String, Schema> fotoProdutoInput = ModelConverters.getInstance().read(FotoProdutoInput.class);
//
//        Map<String, Schema> usuariosEmbeddedModelOpenApi = ModelConverters.getInstance().read(UsuariosEmbeddedModelOpenApi.class);
//        Map<String, Schema> usuariosCollectionModelOpenApi = ModelConverters.getInstance().read(UsuariosCollectionModelOpenApi.class);
//        Map<String, Schema> usuarioComSenhaInput = ModelConverters.getInstance().read(UsuarioComSenhaInput.class);
//        Map<String, Schema> usuarioInput = ModelConverters.getInstance().read(UsuarioInput.class);
//        Map<String, Schema> usuarioNovaSenhaInput = ModelConverters.getInstance().read(UsuarioNovaSenhaInput.class);
//
//        Map<String, Schema> usuarioGruposCollectionModelOpenApi = ModelConverters.getInstance().read(UsuarioGruposCollectionModelOpenApi.class);
//        Map<String, Schema> usuarioGruposEmbeddedModelOpenApi = ModelConverters.getInstance().read(UsuarioGruposEmbeddedModelOpenApi.class);
//        Map<String, Schema> usuarioGrupoHateoasOpenApi = ModelConverters.getInstance().read(UsuarioGrupoHateoasOpenApi.class);
//
//        Map<String, Schema> vendaDiariaHateoasOpenApi = ModelConverters.getInstance().read(VendaDiariaHateoasOpenApi.class);
//
//
//        Map<String, Schema> links = ModelConverters.getInstance().read(LinksModelOpenApi.class);
//        Map<String, Schema> rel = ModelConverters.getInstance().read(LinksModelOpenApi.LinkModel.class);
//
//
//        schemaMap.putAll(vendaDiariaHateoasOpenApi);
//
//        schemaMap.putAll(usuarioGruposCollectionModelOpenApi);
//        schemaMap.putAll(usuarioGruposEmbeddedModelOpenApi);
//        schemaMap.putAll(usuarioGrupoHateoasOpenApi);
//
//        schemaMap.putAll(usuariosCollectionModelOpenApi);
//        schemaMap.putAll(usuariosEmbeddedModelOpenApi);
//        schemaMap.putAll(usuarioComSenhaInput);
//        schemaMap.putAll(usuarioInput);
//        schemaMap.putAll(usuarioNovaSenhaInput);
//
//        schemaMap.putAll(restauranteProdutoFotoHateoasOpenApi);
//        schemaMap.putAll(fotoProdutoInput);
//
//        schemaMap.putAll(gruposPermissaoCollectionModelOpenApi);
//        schemaMap.putAll(gruposPermissaoEmbeddedModelOpenApi);
//        schemaMap.putAll(grupoPermissaoHateoasOpenApi);
//
//        schemaMap.putAll(produtoInput);
//        schemaMap.putAll(restauranteProdutosCollectionModelOpenApi);
//        schemaMap.putAll(restauranteProdutosEmbeddedModelOpenApi);
//        schemaMap.putAll(restauranteProdutoHateoasOpenApi);
//
//
//        schemaMap.putAll(restauranteResponsaveisCollectionModelOpenApi);
//        schemaMap.putAll(restauranteResponsaveisEmbeddedModelOpenApi);
//        schemaMap.putAll(restauranteResponsaveisHateoasOpenApi);
//
//        schemaMap.putAll(estadosCollectionModelOpenApi);
//        schemaMap.putAll(estadosEmbeddedModelOpenApi);
//        schemaMap.putAll(estadoInput);
//
//
//        schemaMap.putAll(cidadesCollectionModelOpenApi);
//        schemaMap.putAll(cidadesEmbeddedModelOpenApi);
//        schemaMap.putAll(cidadeHateoasOpenApi);
//        schemaMap.putAll(estadoHateoasOpenApi);
//        schemaMap.putAll(cidadeInput);
//        schemaMap.putAll(estadoIdInput);
//
//
//
//        schemaMap.putAll(gruposCollectionModelOpenApi);
//        schemaMap.putAll(gruposEmbeddedModelOpenApi);
//        schemaMap.putAll(grupoHateoasOpenApi);
//        schemaMap.putAll(grupoInput);
//        schemaMap.putAll(formaPagamentoInput);
//
//        schemaMap.putAll(cozinhasPagedListModelOpenApi);
//        schemaMap.putAll(cozinhasCollectionModelOpenApiPageable);
//        schemaMap.putAll(cozinhasCollectionModelOpenApiPageableSort);
//        schemaMap.putAll(cozinhasPagedCollectionModelOpenApi);
//        schemaMap.putAll(cozinhasPagedCollectionModelOpenApi2);
//
//        schemaMap.putAll(cozinhasCollectionModelOpenApi);
//        schemaMap.putAll(cozinhasEmbeddedModelOpenApi);
//        schemaMap.putAll(cozinhaHateoasOpenApi);
//        schemaMap.putAll(cozinhaInput);
//
//        schemaMap.putAll(pedidoInput);
//        schemaMap.putAll(pedidoHateoasOpenApi);
//        schemaMap.putAll(pedidosCollectionModelOpenApi);
//        schemaMap.putAll(pedidosEmbeddedModelOpenApi);
//        schemaMap.putAll(pedidoResumoHateoasOpenApi);
//        schemaMap.putAll(pedidosPagedCollectionModelOpenApi);
//        schemaMap.putAll(pedidosPagedCollectionModelOpenApiPedidoPagedCollection);
//
//        schemaMap.putAll(enderecoHateoasOpenApi);
//
//        schemaMap.putAll(cidadeResumoHateoasOpenApi);
//
//        schemaMap.putAll(formasPagamentoCollectionModelOpenApi);
//        schemaMap.putAll(formasPagamentoEmbeddedModelOpenApi);
//        schemaMap.putAll(formaPagamentoHateoasOpenApi);
//
//        schemaMap.putAll(restauranteNomeHateoasOpenApi);
//
//        schemaMap.putAll(usuarioHateoasOpenApi);
//
//        schemaMap.putAll(itemPedidoHateoasOpenApi);
//        schemaMap.putAll(enderecoInput);
//        schemaMap.putAll(itemPedidoInput);
//
//        schemaMap.putAll(pedidoResumoFilterOpenApi);
//
//        schemaMap.putAll(restaurantesCollectionModelOpenApi);
//        schemaMap.putAll(restaurantesEmbeddedModelOpenApi);
//        schemaMap.putAll(restauranteHateoasOpenApi);
//        schemaMap.putAll(restauranteInput);
//        schemaMap.putAll(restauranteParcialModelOpenApi);
//        schemaMap.putAll(restauranteViewDTO);
//        schemaMap.putAll(cozinhaViewDTO);
//        schemaMap.putAll(enderecoDTO);
//        schemaMap.putAll(restauranteNomeOpenApi);
//
//        schemaMap.putAll(links);
//        schemaMap.putAll(rel);
//
//        schemaMap.putAll(errorNotFound);
//        schemaMap.putAll(errorGone);
//        schemaMap.putAll(errorBadRequest);
//        schemaMap.putAll(errorMediaTypeNotSupported);
//        schemaMap.putAll(errorInternalServer);
//
//        return schemaMap;
//
//    }

}
