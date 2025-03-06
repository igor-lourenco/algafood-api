package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Restaurante Nome Output")
@Data
public class RestauranteNomeHateoasOpenApi {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;
//    @ApiModelProperty(example = "Thai Gourmet", position = 5)
    private String nome;

    private Links _links;
}