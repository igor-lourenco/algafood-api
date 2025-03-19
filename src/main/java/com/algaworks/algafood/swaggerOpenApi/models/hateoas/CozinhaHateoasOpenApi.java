package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Cozinha Output")
@Data
public class CozinhaHateoasOpenApi {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Tailandesa")
    private String nome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}