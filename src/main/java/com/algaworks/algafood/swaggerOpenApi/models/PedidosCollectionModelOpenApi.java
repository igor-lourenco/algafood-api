package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PedidosEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de pedidos que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Pedidos Collection")
@Data
public class PedidosCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private PedidosEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
