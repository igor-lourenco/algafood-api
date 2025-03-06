package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.EstadosEmbeddedModelOpenApi;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de cozinhas que implementa o hateoas, essa classe serve apenas para fins de documentação. */
//@ApiModel("Estados Collection")
@Data
public class EstadosCollectionModelOpenApi {

    private EstadosEmbeddedModelOpenApi _embedded;
    private Links _links;
}
