package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Permissões do Grupo Embedded")
@Data
public class GruposPermissaoEmbeddedModelOpenApi {

    private List<GrupoPermissaoHateoasOpenApi> grupos_permissao;
}