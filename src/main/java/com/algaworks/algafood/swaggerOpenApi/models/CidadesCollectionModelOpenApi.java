package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CidadesEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de cidades que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Cidades Collection")
@Data
public class CidadesCollectionModelOpenApi {

    private CidadesEmbeddedModelOpenApi _embedded;
    private Links _links;
}
