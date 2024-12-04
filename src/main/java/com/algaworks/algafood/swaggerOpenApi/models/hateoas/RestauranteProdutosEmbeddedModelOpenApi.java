package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Produtos do restaurante Embedded")
@Data
public class RestauranteProdutosEmbeddedModelOpenApi {

    private List<RestauranteProdutoHateoasOpenApi> produtos;
}