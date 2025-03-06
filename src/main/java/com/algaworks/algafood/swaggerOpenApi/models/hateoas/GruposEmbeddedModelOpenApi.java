package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Grupos Embedded")
@Data
public class GruposEmbeddedModelOpenApi {

    private List<GrupoHateoasOpenApi> grupos;
}