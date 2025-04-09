package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CidadesEmbeddedModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de cidades que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Cidades Collection V2")
@Data
public class CidadesCollectionModelOpenApiV2 {

    @Schema(name = "_embedded")
    private CidadesEmbeddedModelOpenApiV2 _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
