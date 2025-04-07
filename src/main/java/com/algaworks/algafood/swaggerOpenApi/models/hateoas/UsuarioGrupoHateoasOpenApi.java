package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Grupo do usuário Output")
@Data
public class UsuarioGrupoHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Grupo 1")
    private String nome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}