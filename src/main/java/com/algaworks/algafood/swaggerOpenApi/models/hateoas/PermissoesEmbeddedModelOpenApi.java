package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Permissões Embedded")
@Data
public class PermissoesEmbeddedModelOpenApi {

    private List<PermissaoHateoasOpenApi> permissoes;
}