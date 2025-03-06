package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Estado Output")
@Data
public class EstadoHateoasOpenApi {

//    @ApiModelProperty(example = "1")
    private Long id;
//    @ApiModelProperty(example = "Minas Gerais")
    private String nome;

    private Links _links;
}