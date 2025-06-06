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
public @interface QueryParameter {

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
    @interface Pageable { }

    @Deprecated // Squiggly foi depreciado no Spring versão 3.x ou superior
    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Parameter(
        in = ParameterIn.QUERY,
        name = "apenasOsCampos",
        description = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
        schema = @Schema(example = "_embedded[],_links")
    )
    @interface Squiggly { }


    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Parameters({
        @Parameter(
            in = ParameterIn.QUERY,
            name = "clienteId",
            description = "ID do cliente",
            schema = @Schema(example = "1")
        ),
        @Parameter(
            in = ParameterIn.QUERY,
            name = "restauranteId",
            description = "ID do restaurante",
            schema = @Schema(example = "1")
        ),
        @Parameter(
            in = ParameterIn.QUERY,
            name = "dataCriacaoInicio",
            description = "Data inicial de criação padrão yyyy-MM-ddTHH:mm:ssZ",
            schema = @Schema(example = "2024-09-17T08:48:56Z")
        ),
        @Parameter(
            in = ParameterIn.QUERY,
            name = "dataCriacaoFim",
            description = "Data final de criação padrão yyyy-MM-ddTHH:mm:ssZ",
            schema = @Schema(example = "2024-09-17T08:48:56Z")
        )
    })
    @interface PedidoFilter{ }


    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Parameters(value = {
        @Parameter(
            in = ParameterIn.QUERY,
            name = "projecao",
            description = "Define a projeção desejada para o retorno. Se não passar nenhum parâmetro, o retorno será todos os campos por padrão. Valores permitidos: \n"
                + "  - apenas-nome: Retorna apenas os campos id e nome dos restaurantes.\n"
                + "  - resumo: Retorna uma visão resumida com campos adicionais.\n",
            schema = @Schema(example = "apenas-nome")
        )
    })
    @interface JsonFilter{ }


    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Parameter(
        in = ParameterIn.HEADER,
        name = "accept",
        description = "Especifica o tipo de mídia que a API retorna no corpo da resposta: \n",
        required = true,
        schema = @Schema(example = "image/jpeg")
    )
    @interface Foto{ }

    @Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Parameters(value = {
        @Parameter(
            in = ParameterIn.HEADER,
            name = "accept",
            description = "Especifica o tipo de retorno que a API retorna no corpo da resposta: \n",
            schema = @Schema(example = "application/pdf,application/json")
        ),
        @Parameter(
            in = ParameterIn.QUERY,
            name = "restauranteId",
            description = "ID do restaurante",
            schema = @Schema(type = "int", example = "1")
        ),
        @Parameter(
            in = ParameterIn.QUERY,
            name = "dataCriacaoInicio",
            description = "Data/Hora inicial da criação do pedido",
            schema = @Schema(type = "date", example = "05/09/2024")
        ),
        @Parameter(
            in = ParameterIn.QUERY,
            name = "dataCriacaoFim",
            description = "Data/Hora final da criação do pedido",
            schema = @Schema(type = "date", example = "05/09/2025")
        ),
    })
    @interface FiltroVendaDiaria{ }
}
