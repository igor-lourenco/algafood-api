package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Objeto Permissão do Grupo")
@Data
public class GrupoPermissaoHateoasOpenApi {

    @ApiModelProperty(example = "1")
    private Long id;
    @ApiModelProperty(example = "CONSULTAR_COZINHAS")
    private String nome;
    @ApiModelProperty(example = "Permite consultar cozinhas")
    private String descricao;

    private Links _links;
}