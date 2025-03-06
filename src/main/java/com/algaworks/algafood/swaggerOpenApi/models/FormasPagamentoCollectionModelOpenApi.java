package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.FormasPagamentoEmbeddedModelOpenApi;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de formas de pagamento que implementa o hateoas, essa classe serve apenas para fins de documentação. */
//@ApiModel("Formas Pagamento Collection")
@Data
public class FormasPagamentoCollectionModelOpenApi {

    private FormasPagamentoEmbeddedModelOpenApi _embedded;
    private Links _links;
}
