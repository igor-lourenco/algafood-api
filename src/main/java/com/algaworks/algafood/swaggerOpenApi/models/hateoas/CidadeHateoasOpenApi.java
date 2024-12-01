package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Objeto Cidade")
@Data
public class CidadeHateoasOpenApi {
    @ApiModelProperty(example = "1", position = 0)
    private Long id;

    @ApiModelProperty(example = "Uberlândia", position = 5)
    private String nome;

    @ApiModelProperty(position = 10)
    private EstadoHateoasOpenApi estado;
    private Links _links;
}