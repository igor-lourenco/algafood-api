package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.FormasPagamentoEmbeddedModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de formas de pagamento que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Formas Pagamento Collection")
@Data
public class FormasPagamentoCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private FormasPagamentoEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
