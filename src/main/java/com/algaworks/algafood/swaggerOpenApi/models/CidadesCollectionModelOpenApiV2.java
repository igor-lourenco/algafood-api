package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CidadesEmbeddedModelOpenApiV2;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de cidades que implementa o hateoas, essa classe serve apenas para fins de documentação. */
//@ApiModel("Cidades Collection V2")
@Data
public class CidadesCollectionModelOpenApiV2 {

    private CidadesEmbeddedModelOpenApiV2 _embedded;
    private Links _links;
}
