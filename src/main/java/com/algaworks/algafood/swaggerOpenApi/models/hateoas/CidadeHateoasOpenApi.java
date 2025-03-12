package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Cidade Output")
@Data
public class CidadeHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Uberlândia")
    private String nome;

    private EstadoHateoasOpenApi estado;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}