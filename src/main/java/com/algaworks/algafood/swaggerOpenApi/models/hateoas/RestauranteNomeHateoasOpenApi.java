package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Restaurante Nome Output")
@Data
public class RestauranteNomeHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String nome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}