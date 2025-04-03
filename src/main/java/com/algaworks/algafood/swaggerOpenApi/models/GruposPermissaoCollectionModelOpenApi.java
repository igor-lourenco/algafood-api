package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GruposPermissaoEmbeddedModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de grupos permissão que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Permissão dos Grupos Collection")
@Data
public class GruposPermissaoCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private GruposPermissaoEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
