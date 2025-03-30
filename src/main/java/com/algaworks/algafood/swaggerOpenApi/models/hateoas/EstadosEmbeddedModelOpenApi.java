package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Estados Embedded")
@Data
public class EstadosEmbeddedModelOpenApi {

    private List<EstadoHateoasOpenApi> estados;
}