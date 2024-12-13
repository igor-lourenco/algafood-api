package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorInternalServerError;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorMediaTypeNotSupported;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedModelOpenApiV2;
import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Links;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.*;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/** Essa classe configura o Springfox de maneira que ele gere automaticamente a documentação da API baseada
 * nos controladores e endpoints presentes na aplicação */
@Configuration
//@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // Importa classe de configuração para adicionar os campos obrigatórios mapeados com as anotações do Bean Validation na documentação em HTML de
public class SpringFoxConfigV2 implements WebMvcConfigurer {

    @Bean
    public Docket apiDocketV2() {

        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.OAS_30) // Cria uma instância de Docket configurada para utilizar o tipo de documentação Swagger 2.0
            .groupName("V2")
            .select() // Inicia um builder que permite especificar quais controladores e endpoints serão incluídos na documentação.

//          .apis(RequestHandlerSelectors.any()) // Configura o Docket para incluir todos os controladores disponíveis na aplicação.
            .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api.controllers")) // especifica o pacote onde estão os controladores da aplicação.

            .paths(PathSelectors.ant("/v2/**")) // Especifica que qualquer endpoint da Aplicação será documentado.

            .build() // Finaliza a configuração do Docket e retorna a instância pronta para ser gerenciada pelo Spring.

            .apiInfo(apiInfoV2())
            .tags(new Tag("Cidades", "Gerencia as cidades"), // Cria tag para ser mapeada com a tag declarada em CidadeController para ser visualizada na documentação.
                new Tag("Cozinhas", "Gerencia as cozinhas"),
                new Tag("Root entry point", "Ponto incial(acesso as APIs) da aplicação")
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
                typeResolver.resolve(StandardErrorInternalServerError.class))

            .directModelSubstitute(Links.class, LinksModelOpenApi.class)

            .alternateTypeRules(
                AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaDTOV2.class), // Substitui Page<CozinhaDTO> pelo CozinhaPageModelOpenApi para mostrar os campos corretamente na documentação
                    CozinhasPagedModelOpenApiV2.class))


            .ignoredParameterTypes( // Adiciona essa classe passado no parâmetro do método das APIs(FormaPagamentoController por exemplo) para ser ignorado e não gere documentação para esses tipos específicos.
                CollectionModel.class,
                PagedModel.class,
                CidadeDTOV2.class,
                RootEntryPointDTO.class
            )

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
    private ApiInfo apiInfoV2() {
        return new ApiInfoBuilder()
            .title("Alga food API")
            .description("APIs aberta versão 2 para clientes e restaurantes")
            .version("2")
            .contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
            .build();
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
