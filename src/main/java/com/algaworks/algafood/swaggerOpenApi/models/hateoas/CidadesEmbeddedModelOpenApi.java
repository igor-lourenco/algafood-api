package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Cidades Embedded")
@Data
public class CidadesEmbeddedModelOpenApi {

    private List<CidadeHateoasOpenApi> cidades;
}