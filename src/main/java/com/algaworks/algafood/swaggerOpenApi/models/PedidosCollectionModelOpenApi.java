package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PedidosEmbeddedModelOpenApi;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de pedidos que implementa o hateoas, essa classe serve apenas para fins de documentação. */
//@ApiModel("Pedidos Collection")
@Data
public class PedidosCollectionModelOpenApi {

    private PedidosEmbeddedModelOpenApi _embedded;
    private Links _links;
}
