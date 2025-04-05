package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Usuários Embedded")
@Data
public class UsuariosEmbeddedModelOpenApi {

    private List<UsuarioHateoasOpenApi> usuarios;
}