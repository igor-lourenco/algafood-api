package com.algaworks.algafood.swaggerOpenApi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/** Essa classe documenta os campos da paginação para requisição da API, essa classe serve apenas para fins de documentação. */
@Schema(name = "Restaurante Parcial")
@Getter
@Setter
public class RestauranteParcialModelOpenApi {

    @Schema(example = "Thai Gourmet")
    private String nome;

    @Schema(example = "10.00")
    private String taxaFrete;
}
