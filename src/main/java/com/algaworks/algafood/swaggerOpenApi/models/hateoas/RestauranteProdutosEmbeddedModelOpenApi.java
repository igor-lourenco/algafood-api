package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Produtos do restaurante Embedded")
@Data
public class RestauranteProdutosEmbeddedModelOpenApi {

    private List<RestauranteProdutoHateoasOpenApi> produtos;
}