package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Cozinhas Embedded V2")
@Data
public class CozinhasEmbeddedModelOpenApiV2 {

    private List<CozinhaHateoasOpenApiV2> cozinhas;
}