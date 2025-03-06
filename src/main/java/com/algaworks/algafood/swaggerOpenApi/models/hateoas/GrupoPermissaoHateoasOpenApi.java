package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Permissão do Grupo Output")
@Data
public class GrupoPermissaoHateoasOpenApi {

//    @ApiModelProperty(example = "1")
    private Long id;
//    @ApiModelProperty(example = "CONSULTAR_COZINHAS")
    private String nome;
//    @ApiModelProperty(example = "Permite consultar cozinhas")
    private String descricao;

    private Links _links;
}