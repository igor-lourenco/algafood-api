package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name ="Restaurantes Embedded")
@Data
public class RestaurantesEmbeddedModelOpenApi {

    private List<RestauranteHateoasOpenApi> restaurantes;
}