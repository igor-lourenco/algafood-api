package com.algaworks.algafood.core.configs;

import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.controllers.exceptions.openApi.StandardErrorInternalServerError;
import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

/** Essa classe configura o Springfox de maneira que ele gere automaticamente a documentação da API baseada
 * nos controladores e endpoints presentes na aplicação */
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) // Importa classe de configuração para adicionar os campos obrigatórios mapeados com as anotações do Bean Validation na documentação em HTML de
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {

        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2) // Cria uma instância de Docket configurada para utilizar o tipo de documentação Swagger 2.0
            .select() // Inicia um builder que permite especificar quais controladores e endpoints serão incluídos na documentação.

//          .apis(RequestHandlerSelectors.any()) // Configura o Docket para incluir todos os controladores disponíveis na aplicação.
            .apis(Predicates.and(
                RequestHandlerSelectors.basePackage("com.algaworks.algafood.api.controllers") // especifica o pacote onde estão os controladores da aplicação.
//              ,RequestHandlerSelectors.basePackage("com.algaworks.algafood.api.outroController") // se quiser adicionar outro pacote de controller
            ))

            .paths(PathSelectors.any()) // Especifica que qualquer endpoint da Aplicação será documentado.
//          .paths(PathSelectors.ant("/restaurantes/*")) // Especifica que somente os endpoints que começam com /restaurantes/ e têm exatamente um segmento adicional serão documentados.

            .build() // Finaliza a configuração do Docket e retorna a instância pronta para ser gerenciada pelo Spring.

            .apiInfo(apiInfo())
            .tags(new Tag("Cidades", "Gerencia as cidades"), // Cria tag para ser mapeada com a tag declarada em CidadeController para ser visualizada na documentação.
                new Tag("Grupos", "Gerencia os grupos"))

            .useDefaultResponseMessages(false) // Desabilita a visualização padrão do status code de erro 4xx e 5xx para poder implementar manualmente
            .globalResponseMessage(RequestMethod.GET, globalGETResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET
            .globalResponseMessage(RequestMethod.POST, globalPostPutResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET
            .globalResponseMessage(RequestMethod.PUT, globalPostPutResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET
            .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages()) // Especifica as mensagens de erro padrão global para todas as APIs para o verbo GET

            .additionalModels(typeResolver.resolve(StandardError.class), // Adiciona a classe como um modelo extra para aparecer na documentação em HTML
                typeResolver.resolve(StandardErrorInternalServerError.class),
                typeResolver.resolve(StandardErrorInternalServerError.class))
            ;

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      arquivo jar: springfox-swagger-ui
        registry.addResourceHandler("swagger-ui.html") // url da documentação: http://localhost:8080/swagger-ui.html
            .addResourceLocations("classpath:/META-INF/resources/"); // caminho onde está o arquivo swagger-ui.html

        registry.addResourceHandler("/webjars/**") // mapeia os arquivos estáticos css, javascript, ícones e afins do Swagger
            .addResourceLocations("classpath:/META-INF/resources/webjars/"); // caminho onde está os arquivos estáticos

    }

/** Configura as informações da API que serão exibidas na documentação gerada pelo Swagger.*/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Alga food API")
            .description("API aberta para clientes e restaurantes")
            .version("1")
            .contact(new Contact("Algaworks", "https://www.algaworks.com", "contato@algaworks.com"))
            .build();
    }

/** Método com a lista de mensagens de erro global da aplicação para todas as APIs do verbo GET para ser visualizada na documentação.  */
    private List<ResponseMessage> globalGETResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .responseModel(new ModelRef("StandardErrorInternalServerError")) // Referência a classe mapeada com o nome 'StandardErrorInternalServerError' como body de resposta desse erro
                .message("Erro interno do servidor")
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build()
        );
    }


/** Método com a lista de mensagens de erro global da aplicação para todas as APIs do verbo POST e PUT para ser visualizada na documentação.  */
    private List<ResponseMessage> globalPostPutResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Requisição inválida (erro do cliente)")
                .responseModel(new ModelRef("StandardError"))// Referência a classe mapeada com o nome 'StandardError' como body de resposta desse erro
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno no servidor")
                .responseModel(new ModelRef("StandardErrorInternalServerError"))// Referência a classe mapeada com o nome 'StandardErrorInternalServerError' como body de resposta desse erro
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.NOT_ACCEPTABLE.value())
                .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .message("Requisição recusada porque o corpo está em um formato não suportado")
                .responseModel(new ModelRef("StandardErrorMediaTypeNotSupported"))// Referência a classe mapeada com o nome 'StandardErrorMediaTypeNotSupported' como body de resposta desse erro
                .build()
        );
    }

/** Método com a lista de mensagens de erro global da aplicação para todas as APIs do verbo DELETE para ser visualizada na documentação.*/
    private List<ResponseMessage> globalDeleteResponseMessages() {
        return Arrays.asList(
            new ResponseMessageBuilder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Requisição inválida (erro do cliente)")
                .responseModel(new ModelRef("StandardError"))// Referência a classe mapeada com o nome 'StandardError' como body de resposta desse erro
                .build(),
            new ResponseMessageBuilder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Erro interno no servidor")
                .responseModel(new ModelRef("StandardErrorInternalServerError"))// Referência a classe mapeada com o nome 'StandardErrorInternalServerError' como body de resposta desse erro
                .build()
        );
    }
}
