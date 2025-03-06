package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Cozinha Output")
@Data
public class CozinhaHateoasOpenApi {

//    @ApiModelProperty(example = "1")
    private Long id;
//    @ApiModelProperty(example = "Tailandesa")
    private String nome;

    private Links _links;
}