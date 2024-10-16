package com.algaworks.algafood.core.configs;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** Essa classe configura o Springfox de maneira que ele gere automaticamente a documentação da API baseada
 * nos controladores e endpoints presentes na aplicação */
@Configuration
@EnableSwagger2
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket apiDocket() {

        return new Docket(DocumentationType.SWAGGER_2) // Cria uma instância de Docket configurada para utilizar o tipo de documentação Swagger 2.0
            .select() // Inicia um builder que permite especificar quais controladores e endpoints serão incluídos na documentação.

//          .apis(RequestHandlerSelectors.any()) // Configura o Docket para incluir todos os controladores disponíveis na aplicação.
            .apis(Predicates.and(
                RequestHandlerSelectors.basePackage("com.algaworks.algafood.api.controllers") // especifica o pacote onde estão os controladores da aplicação.
//                ,RequestHandlerSelectors.basePackage("com.algaworks.algafood.api.outroController") // se quiser adicionar outro pacote de controller
            ))

            .paths(PathSelectors.any()) // Especifica que qualquer endpoint da Aplicação será documentado.
//          .paths(PathSelectors.ant("/restaurantes/*")) // Especifica que somente os endpoints que começam com /restaurantes/ e têm exatamente um segmento adicional serão documentados.

            .build(); // Finaliza a configuração do Docket e retorna a instância pronta para ser gerenciada pelo Spring.
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      arquivo jar: springfox-swagger-ui
        registry.addResourceHandler("swagger-ui.html") // url da documentação: http://localhost:8080/swagger-ui.html
            .addResourceLocations("classpath:/META-INF/resources/"); // caminho onde está o arquivo swagger-ui.html

        registry.addResourceHandler("/webjars/**") // mapeia os arquivos estáticos css, javascript, ícones e afins do Swagger
            .addResourceLocations("classpath:/META-INF/resources/webjars/"); // caminho onde está os arquivos estáticos

    }
}