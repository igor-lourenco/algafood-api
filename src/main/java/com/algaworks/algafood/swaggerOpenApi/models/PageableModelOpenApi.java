package com.algaworks.algafood.swaggerOpenApi.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Essa classe documenta os campos da paginação para requisição da API, essa classe serve apenas para fins de documentação. */
@ApiModel("Pageable")
@Getter
@Setter
public class PageableModelOpenApi {

    @ApiModelProperty(example = "0", value = "Número da página (começa em 0).")
    private int page;
    @ApiModelProperty(example = "12", value = "Quantidade de elementos por página")
    private int size;
    @ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação")
    private List<String> sort;
}
