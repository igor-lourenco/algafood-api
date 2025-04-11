package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhasEmbeddedModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de cozinhas que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Cozinhas Collection V2")
@Data
public class CozinhasCollectionModelOpenApiV2 {

    @Schema(name = "_embedded")
    private CozinhasEmbeddedModelOpenApiV2 _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
