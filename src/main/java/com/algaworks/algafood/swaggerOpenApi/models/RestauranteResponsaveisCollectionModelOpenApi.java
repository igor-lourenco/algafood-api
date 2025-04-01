package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteResponsaveisEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de resposáveis do restaurante que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Responsavéis do restaurante Collection")
@Data
public class RestauranteResponsaveisCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private RestauranteResponsaveisEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
