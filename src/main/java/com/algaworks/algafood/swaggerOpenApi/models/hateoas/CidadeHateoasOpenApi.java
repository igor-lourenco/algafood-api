package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Cidade Output")
@Data
public class CidadeHateoasOpenApi {

//    @ApiModelProperty(example = "1")
    private Long id;
//    @ApiModelProperty(example = "Uberlândia")
    private String nome;
//    @ApiModelProperty(position = 10)
    private EstadoHateoasOpenApi estado;

    private Links _links;
}