package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorInternalServerError;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorMediaTypeNotSupported;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.PageableModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.PedidosPagedModelOpenApi;
import com.fasterxml.classmate.TypeResolver;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Links;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.json.JacksonModuleRegistrar;
import springfox.documentation.spring.web.plugins.Docket;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/** Essa classe configura o Springfox de maneira que ele gere automaticamente a documentação da API baseada
 * nos controladores e endpoints presentes na aplicação */
@Configuration
//@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // Importa classe de configuração para adicionar os campos obrigatórios mapeados com as anotações do Bean Validation na documentação em HTML de
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {

        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30) // Cria uma instância de Docket configurada para utilizar o tipo de documentação Swagger 2.0
            .select() // Inicia um builder que permite especificar quais controladores e endpoints serão incluídos na documentação.

//          .apis(RequestHandlerSelectors.any()) // Configura o Docket para incluir todos os controladores disponíveis na aplicação.
            .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api.controllers")) // especifica o pacote onde estão os controladores da aplicação.

            .paths(PathSelectors.any()) // Especifica que qualquer endpoint da Aplicação será documentado.
//          .paths(PathSelectors.ant("/restaurantes/*")) // Especifica que somente os endpoints que começam com /restaurantes/ e têm exatamente um segmento adicional serão documentados.

            .build() // Finaliza a configuração do Docket e retorna a instância pronta para ser gerenciada pelo Spring.

            .apiInfo(apiInfo())
            .tags(new Tag("Cidades", "Gerencia as cidades"), // Cria tag para ser mapeada com a tag declarada em CidadeController para ser visualizada na documentação.
                new Tag("Grupos", "Gerencia os grupos de usuários"),
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Formas de pagamento", "Gerencia as formas de pagamentos"),
                new Tag("Pedidos", "Gerencia os pedidos"),
                new Tag("Restaurantes", "Gerencia os restaurantes"),
                new Tag("Estados", "Gerencia os estados"),
                new Tag("Produtos", "Gerencia os produtos de restaurantes"),
                new Tag("Usuarios", "Gerencia os usuários"),
                new Tag("Estatísticas", "Estatísticas de AlgaFood"),
                new Tag("Permissões", "Gerencia as permissões")
                )

            .useDefaultResponseMessages(false) // Desabilita a visualização padrão do status code de erro 4xx e 5xx para poder implementar manualmente
            .globalResponses(HttpMethod.GET, globalGETResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET
            .globalResponses(HttpMethod.POST, globalPostPutResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET
            .globalResponses(HttpMethod.PUT, globalPostPutResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET
            .globalResponses(HttpMethod.DELETE, globalDeleteResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET

            .additionalModels(typeResolver.resolve(StandardError.class), // Adiciona a classe como um modelo extra para aparecer na documentação em HTML
                typeResolver.resolve(StandardErrorMediaTypeNotSupported.class),
                typeResolver.resolve(StandardErrorBadRequest.class),
                typeResolver.resolve(StandardErrorNotFound.class),
//                typeResolver.resolve(HateoasModelOpenApi.class),
                typeResolver.resolve(StandardErrorInternalServerError.class))

            .directModelSubstitute(Pageable.class, PageableModelOpenApi.class) //Substitui diretamente uma classe de modelo pelo substituto fornecido, para mostrar os campos corretamente na documentação
            .directModelSubstitute(Links.class, LinksModelOpenApi.class)

            .alternateTypeRules(
                AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaDTO.class), // Substitui Page<CozinhaDTO> pelo CozinhaPageModelOpenApi para mostrar os campos corretamente na documentação
                    CozinhasPagedModelOpenApi.class))
            .alternateTypeRules(
                AlternateTypeRules.newRule(typeResolver.resolve(Page.class, PedidoResumoDTO.class), // Substitui Page<CozinhaDTO> pelo CozinhaPageModelOpenApi para mostrar os campos corretamente na documentação
                    PedidosPagedModelOpenApi.class))


            .ignoredParameterTypes( // Adiciona essa classe passado no parâmetro do método das APIs(FormaPagamentoController por exemplo) para ser ignorado e não gere documentação para esses tipos específicos.
                ServletWebRequest.class,
                MappingJacksonValue.class,
                URL.class,
                URI.class,
                URLStreamHandler.class,
                Resource.class,
                File.class,
                InputStream.class,
                InputStreamResource.class
            )

//            .globalRequestParameters( // Adiciona parâmetros padrão que serão aplicados a todas as APIs da documentação.
//                Collections.singletonList(
//                    new RequestParameterBuilder()
//                        .name("apenasOsCampos")
//                        .description("Nomes das propriedades para filtrar na resposta, separados por vírgula")
//                        .in(ParameterType.QUERY)
//                        .required(true)
//                        .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
//                        .build())
//            )

            ;

    }

//    TODO: No Spring Boot com a versão 3.0.0 não é mais necessário adicionar as configurações do WebMvcConfigurer,
//     a página de documentação do Spring Fox é configurada automaticamente.
//     Acessando a documentação
//     Utilize a URL http://localhost:8080/swagger-ui/index.html.
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////      arquivo jar: springfox-swagger-ui
//        registry.addResourceHandler("swagger-ui.html") // url da documentação: http://localhost:8080/swagger-ui.html
//            .addResourceLocations("classpath:/META-INF/resources/"); // caminho onde está o arquivo swagger-ui.html
//
//        registry.addResourceHandler("/webjars/**") // mapeia os arquivos estáticos css, javascript, ícones e afins do Swagger
//            .addResourceLocations("classpath:/META-INF/resources/webjars/"); // caminho onde está os arquivos estáticos
//
//    }

/** Configura as informações da API que serão exibidas na documentação gerada pelo Swagger.*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Alga food API")
            .description("API aberta para clientes e restaurantes")
            .version("1")
            .contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
            .build();
    }

//    TODO: Esse @Bean foi implementado apra a versão 3.0 do Spring Fox e serve para resolver a exception causada ao
//     serializar um OffsetDateTime em algum examplo da nossa documentação, Exception:
//     Caused by: com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Java 8 date/time type `java.time.
    @Bean
    public JacksonModuleRegistrar springFoxJacksonConfig() {
        return objectMapper -> objectMapper.registerModule(new JavaTimeModule());
    }


/** Método com a lista de mensagens de erro global da aplicação para todas as APIs do verbo GET para ser visualizada na documentação.  */
    private List<Response> globalGETResponseMessages() {
        return Arrays.asList(
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Erro interno do servidor")
                .representation(MediaType.APPLICATION_JSON)
                .apply(getErro500ModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build()
        );
    }


/** Método com a lista de mensagens de erro global da aplicação para todas as APIs do verbo POST e PUT para ser visualizada na documentação.  */
    private List<Response> globalPostPutResponseMessages() {
        return Arrays.asList(
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description("Requisição inválida (erro do cliente)")
//                .responseModel(new ModelRef("Erro Padrão"))// Referência a classe mapeada com o nome 'StandardError' como body de resposta desse erro
                .representation(MediaType.APPLICATION_JSON)
                .apply(getErroPadraoModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Erro interno no servidor")
//                .responseModel(new ModelRef("Erro 500"))// Referência a classe mapeada com o nome 'StandardErrorInternalServerError' como body de resposta desse erro
                .representation(MediaType.APPLICATION_JSON)
                .apply(getErro500ModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.NOT_ACCEPTABLE.value()))
                .description("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value()))
                .description("Requisição recusada porque o corpo está em um formato não suportado")
//                .responseModel(new ModelRef("Erro 415"))// Referência a classe mapeada com o nome 'StandardErrorMediaTypeNotSupported' como body de resposta desse erro
                .representation(MediaType.APPLICATION_JSON)
                .apply(getErro415ModelReference())
                .build()
        );
    }

/** Método com a lista de mensagens de erro global da aplicação para todas as APIs do verbo DELETE para ser visualizada na documentação.*/
    private List<Response> globalDeleteResponseMessages() {
        return Arrays.asList(
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.BAD_REQUEST.value()))
                .description("Requisição inválida (erro do cliente)")
//                .responseModel(new ModelRef("Erro Padrão"))// Referência a classe mapeada com o nome 'StandardError' como body de resposta desse erro
                .representation(MediaType.APPLICATION_JSON)
                .apply(getErroPadraoModelReference())
                .build(),
            new ResponseBuilder()
                .code(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                .description("Erro interno no servidor")
//                .responseModel(new ModelRef("Erro 500"))// Referência a classe mapeada com o nome 'StandardErrorInternalServerError' como body de resposta desse erro
                .representation(MediaType.APPLICATION_JSON)
                .apply(getErro500ModelReference())
                .build()
        );
    }


    private Consumer<RepresentationBuilder> getErro500ModelReference() {
        return r -> r.model(m -> m.name("Erro 500")
            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                q -> q.name("Erro 500").namespace("com.algaworks.algafood.swaggerOpenApi.exceptions")))));
    }

    private Consumer<RepresentationBuilder> getErroPadraoModelReference() {
        return r -> r.model(m -> m.name("Erro Padrão")
            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                q -> q.name("Erro Padrão").namespace("com.algaworks.algafood.api.controllers.exceptions")))));
    }

    private Consumer<RepresentationBuilder> getErro415ModelReference() {
        return r -> r.model(m -> m.name("Erro 415")
            .referenceModel(ref -> ref.key(k -> k.qualifiedModelName(
                q -> q.name("Erro 415").namespace("com.algaworks.algafood.swaggerOpenApi.exceptions")))));
    }
}
