package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Grupos do usuário Embedded")
@Data
public class UsuarioGruposEmbeddedModelOpenApi {

    private List<UsuarioGrupoHateoasOpenApi> grupos_usuario;
}