package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Cozinha Output V2")
@Data
public class CozinhaHateoasOpenApiV2 {

    @Schema(example = "1")
    private Long cidadeId;

    @Schema(example = "Tailandesa")
    private String cidadeNome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}