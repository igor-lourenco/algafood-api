package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Cidade Output V2")
@Data
public class CidadeHateoasOpenApiV2 {

    @Schema(example = "1")
    private Long id;
    @Schema(example = "Uberlândia")
    private String nome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}