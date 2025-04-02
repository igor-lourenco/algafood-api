package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteProdutosEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção produtos do restaurante que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name ="Produtos do Restaurante Collection")
@Data
public class RestauranteProdutosCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private RestauranteProdutosEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
