package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Objeto Estado")
@Data
public class EstadoHateoasOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "Minas Gerais")
    private String nome;

    private Links _links;
}