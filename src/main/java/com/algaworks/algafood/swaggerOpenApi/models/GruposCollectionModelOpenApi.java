package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GruposEmbeddedModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de grupos que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Grupos Collection")
@Data
public class GruposCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private GruposEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
