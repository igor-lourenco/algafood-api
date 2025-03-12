package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Estado Output")
@Data
public class EstadoHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Minas Gerais")
    private String nome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}