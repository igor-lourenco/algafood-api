package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Cidade Output V2")
@Data
public class CidadeHateoasOpenApiV2 {

//    @ApiModelProperty(example = "1")
    private Long id;
//    @ApiModelProperty(example = "Uberlândia")
    private String nome;

    private Links _links;
}