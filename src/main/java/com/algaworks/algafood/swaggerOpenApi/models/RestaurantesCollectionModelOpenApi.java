package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestaurantesEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção restaurantes que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Restaurantes Collection")
@Data
public class RestaurantesCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private RestaurantesEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
