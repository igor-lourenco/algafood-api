package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Produtos do restaurante Embedded")
@Data
public class RestauranteProdutosEmbeddedModelOpenApi {

    private List<RestauranteProdutoHateoasOpenApi> produtos;
}