package com.algaworks.algafood.swaggerOpenApi.models.pages;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** Essa anotação é usada para reduzir a repetição de código quando você precisar documentar os mesmos parâmetros de
 * paginação em diferentes endpoints da API.*/
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Parameters({
    @Parameter(
        in = ParameterIn.QUERY,
        name = "page",
        description = "Número da página (0..N)",
        schema = @Schema(type = "integer", defaultValue = "0")
    ),
    @Parameter(
        in = ParameterIn.QUERY,
        name = "size",
        description = "Quantidade de elementos por página",
        schema = @Schema(type = "integer", defaultValue = "12")
    ),
    @Parameter(
        in = ParameterIn.QUERY,
        name = "sort",
        description = "Critério de ordenação: propriedade, (asc | desc)",
        schema = @Schema(example = "nome,desc")
    ),
})
public @interface PageableParameter { }
