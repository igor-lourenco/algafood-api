package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.UsuariosEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de usuarios que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Usuários Collection")
@Data
public class UsuariosCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private UsuariosEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
