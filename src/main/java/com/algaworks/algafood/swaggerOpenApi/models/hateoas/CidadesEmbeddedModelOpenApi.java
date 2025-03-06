package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Cidades Embedded")
@Data
public class CidadesEmbeddedModelOpenApi {

    private List<CidadeHateoasOpenApi> cidades;
}