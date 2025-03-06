package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Grupo Output")
@Data
public class GrupoHateoasOpenApi {

//    @ApiModelProperty(example = "1")
    private Long id;
//    @ApiModelProperty(example = "Gerencia")
    private String nome;

    private Links _links;
}