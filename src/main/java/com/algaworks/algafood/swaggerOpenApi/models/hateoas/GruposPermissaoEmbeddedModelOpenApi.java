package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Permissões do Grupo Embedded")
@Data
public class GruposPermissaoEmbeddedModelOpenApi {

    private List<GrupoPermissaoHateoasOpenApi> grupos_permissao;
}