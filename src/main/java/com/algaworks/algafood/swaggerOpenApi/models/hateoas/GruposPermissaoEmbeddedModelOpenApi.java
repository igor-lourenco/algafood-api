package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Permissão do Grupo Embedded")
@Data
public class GruposPermissaoEmbeddedModelOpenApi {

    private List<GrupoPermissaoHateoasOpenApi> grupos_permissao;
}