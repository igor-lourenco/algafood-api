package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PermissoesEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de permissões que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Permissão Collection")
@Data
public class PermissoesCollectionModelOpenApi {

    private PermissoesEmbeddedModelOpenApi _embedded;
    private Links _links;
}
