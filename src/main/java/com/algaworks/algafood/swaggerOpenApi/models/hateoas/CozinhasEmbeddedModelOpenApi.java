package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Cozinhas Embedded")
@Data
public class CozinhasEmbeddedModelOpenApi {

    private List<CozinhaHateoasOpenApi> cozinhas;
}