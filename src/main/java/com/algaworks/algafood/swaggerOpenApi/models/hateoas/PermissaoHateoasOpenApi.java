package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Permissão Output")
@Data
public class PermissaoHateoasOpenApi {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;
//    @ApiModelProperty(example = "CONSULTAR_COZINHAS", position = 5)
    private String nome;
//    @ApiModelProperty(example = "Permite consultar cozinhas", position = 10)
    private String descricao;

    private Links _links;
}