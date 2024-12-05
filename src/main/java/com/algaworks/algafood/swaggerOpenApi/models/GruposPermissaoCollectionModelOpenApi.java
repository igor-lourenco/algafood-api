package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GruposPermissaoEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de grupos permissão que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Permissão dos Grupos Collection")
@Data
public class GruposPermissaoCollectionModelOpenApi {

    private GruposPermissaoEmbeddedModelOpenApi _embedded;
    private Links _links;
}
