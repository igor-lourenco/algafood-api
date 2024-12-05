package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Permissão Embedded")
@Data
public class PermissoesEmbeddedModelOpenApi {

    private List<PermissaoHateoasOpenApi> permissoes;
}