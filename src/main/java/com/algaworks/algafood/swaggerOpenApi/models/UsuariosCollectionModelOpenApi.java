package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.UsuariosEmbeddedModelOpenApi;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de usuarios que implementa o hateoas, essa classe serve apenas para fins de documentação. */
//@ApiModel("Usuários Collection")
@Data
public class UsuariosCollectionModelOpenApi {

    private UsuariosEmbeddedModelOpenApi _embedded;
    private Links _links;
}
