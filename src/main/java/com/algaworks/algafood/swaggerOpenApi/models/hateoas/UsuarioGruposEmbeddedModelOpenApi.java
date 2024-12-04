package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Grupo do usuario Embedded")
@Data
public class UsuarioGruposEmbeddedModelOpenApi {

    private List<UsuarioGrupoHateoasOpenApi> grupos_usuario;
}