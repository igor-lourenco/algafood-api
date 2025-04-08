package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PermissoesEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de permissões que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Permissão Collection")
@Data
public class PermissoesCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private PermissoesEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}

