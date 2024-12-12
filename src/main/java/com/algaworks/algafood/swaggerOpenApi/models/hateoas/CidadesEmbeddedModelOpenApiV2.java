package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Cidades Embedded V2")
@Data
public class CidadesEmbeddedModelOpenApiV2 {

    private List<CidadeHateoasOpenApiV2> cidades;
}