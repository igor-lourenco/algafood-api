package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.UsuarioGruposEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de grupos do usuario que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Grupos do usuário Collection")
@Data
public class UsuarioGruposCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private UsuarioGruposEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}
