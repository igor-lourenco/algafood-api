package com.algaworks.algafood.swaggerOpenApi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/** Essa classe documenta os campos da paginação para requisição da API, essa classe serve apenas para fins de documentação. */
@ApiModel("Restaurante Parcial")
@Getter
@Setter
public class RestauranteParcialModelOpenApi {

    @ApiModelProperty(example = "Thai Gourmet", position = 5)
    private String nome;
    @ApiModelProperty(example = "10.00", position = 10)
    private String taxaFrete;
}
