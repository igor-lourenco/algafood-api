package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Usuários Embedded")
@Data
public class UsuariosEmbeddedModelOpenApi {

    private List<UsuarioHateoasOpenApi> usuarios;
}