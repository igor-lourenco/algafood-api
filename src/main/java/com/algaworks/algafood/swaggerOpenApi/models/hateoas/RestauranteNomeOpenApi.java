package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Restaurante Apenas Nome Output")
@Data
public class RestauranteNomeOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Thai Gourmet")
    private String nome;
}