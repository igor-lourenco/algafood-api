package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Cidades Embedded V2")
@Data
public class CidadesEmbeddedModelOpenApiV2 {

    private List<CidadeHateoasOpenApiV2> cidades;
}