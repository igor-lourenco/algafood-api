package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Grupo do usuário Output")
@Data
public class UsuarioGrupoHateoasOpenApi {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;

//    @ApiModelProperty(example = "Grupo 1", position = 5)
    private String nome;

    private Links _links;
}