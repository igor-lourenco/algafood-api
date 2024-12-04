package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestaurantesEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção restaurantes que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Restaurante Model")
@Data
public class RestaurantesCollectionModelOpenApi {

    private RestaurantesEmbeddedModelOpenApi _embedded;
    private Links _links;
}
