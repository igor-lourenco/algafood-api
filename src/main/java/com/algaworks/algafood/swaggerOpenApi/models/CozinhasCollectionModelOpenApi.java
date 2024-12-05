package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhasEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de cozinhas que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Cozinhas Collection")
@Data
public class CozinhasCollectionModelOpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
}
