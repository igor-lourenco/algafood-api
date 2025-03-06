package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Restaurantes Embedded")
@Data
public class RestaurantesEmbeddedModelOpenApi {

    private List<RestauranteHateoasOpenApi> restaurantes;
}