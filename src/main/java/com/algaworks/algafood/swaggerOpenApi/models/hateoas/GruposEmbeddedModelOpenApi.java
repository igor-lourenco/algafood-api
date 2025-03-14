package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Grupos Embedded")
@Data
public class GruposEmbeddedModelOpenApi {

    private List<GrupoHateoasOpenApi> grupos;
}