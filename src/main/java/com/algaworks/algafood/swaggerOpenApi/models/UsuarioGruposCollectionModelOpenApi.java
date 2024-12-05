package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.UsuarioGruposEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de grupos do usuario que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Grupos do usuário Collection")
@Data
public class UsuarioGruposCollectionModelOpenApi {

    private UsuarioGruposEmbeddedModelOpenApi _embedded;
    private Links _links;
}
