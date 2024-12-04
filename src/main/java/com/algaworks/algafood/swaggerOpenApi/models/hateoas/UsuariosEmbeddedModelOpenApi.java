package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Usuario Embedded")
@Data
public class UsuariosEmbeddedModelOpenApi {

    private List<UsuarioHateoasOpenApi> usuarios;
}