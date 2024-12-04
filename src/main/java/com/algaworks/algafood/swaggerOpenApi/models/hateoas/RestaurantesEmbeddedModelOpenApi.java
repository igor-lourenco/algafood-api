package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Restaurante Embedded")
@Data
public class RestaurantesEmbeddedModelOpenApi {

    private List<RestauranteHateoasOpenApi> restaurantes;
}