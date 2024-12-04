package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteProdutosEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção produtos do restaurante que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Produtos do Restaurante Model")
@Data
public class RestauranteProdutosCollectionModelOpenApi {

    private RestauranteProdutosEmbeddedModelOpenApi _embedded;
    private Links _links;
}
