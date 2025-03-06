package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Cozinhas Embedded V2")
@Data
public class CozinhasEmbeddedModelOpenApiV2 {

    private List<CozinhaHateoasOpenApiV2> cozinhas;
}