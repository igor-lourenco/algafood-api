package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Cidades Embedded")
@Data
public class CidadesEmbeddedModelOpenApi {

    private List<CidadeHateoasOpenApi> cidades;
}