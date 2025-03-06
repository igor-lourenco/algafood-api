package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Cidade Resumida Output ")
@Data
public class CidadeResumoHateoasOpenApi {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;
//    @ApiModelProperty(example = "Uberlândia", position = 5)
    private String nome;
//    @ApiModelProperty(example = "Minas Gerais", position = 10)
    private String estado;

    private Links _links;
}