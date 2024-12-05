package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GruposEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de grupos que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Grupos Collection")
@Data
public class GruposCollectionModelOpenApi {

    private GruposEmbeddedModelOpenApi _embedded;
    private Links _links;
}
