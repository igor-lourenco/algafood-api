package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Cozinhas Embedded")
@Data
public class CozinhasEmbeddedModelOpenApi {

    private List<CozinhaHateoasOpenApi> cozinhas;
}