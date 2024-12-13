package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Cozinha Output V2")
@Data
public class CozinhaHateoasOpenApiV2 {

    @ApiModelProperty(example = "1")
    private Long cidadeId;
    @ApiModelProperty(example = "Tailandesa")
    private String cidadeNome;

    private Links _links;
}