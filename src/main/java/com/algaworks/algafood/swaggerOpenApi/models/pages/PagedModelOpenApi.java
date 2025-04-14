package com.algaworks.algafood.swaggerOpenApi.models.pages;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/** Essa classe é uma representação genérica de uma resposta paginada para APIs que retornam dados em páginas, e define
 *  a estrutura que será usada para documentar e descrever no Swagger (com a ajuda do SpringDoc) como o conteúdo paginado será retornado.*/
@Getter
@Setter
public class PagedModelOpenApi<T> {

    private List<T> content;

    private Long size;
    private Long totalElements;
    private Long totalPages;
    private Long number;
}
