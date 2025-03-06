package com.algaworks.algafood.swaggerOpenApi.models.pages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Essa classe é uma representação genérica de uma resposta paginada para APIs que retornam dados em páginas, e define
 *  a estrutura que será usada para documentar e descrever no Swagger (com a ajuda do SpringFox) como o conteúdo paginado será retornado.*/
@Getter
@Setter
public class PagedModelOpenApi<T> {

    private List<T> content;

//    @ApiModelProperty(example = "12", value = "Quantidade de registros por página")
    private Long size;
//    @ApiModelProperty(example = "50", value = "Total de registros")
    private Long totalElements;
//    @ApiModelProperty(example = "5", value = "Total de páginas")
    private Long totalPages;
//    @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
    private Long number;
}
