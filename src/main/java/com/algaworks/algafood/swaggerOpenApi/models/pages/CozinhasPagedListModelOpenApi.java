package com.algaworks.algafood.swaggerOpenApi.models.pages;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhaHateoasOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
/** Essa classe foi criada para documentar especificamente respostas que retornam as propriedades do objeto Pageable do Spring Data
 *  que contêm objetos do tipo CozinhaDTO.*/
@Schema(name = "Paginação de Cozinhas")
@Data
public class CozinhasPagedListModelOpenApi {

    private List<CozinhaHateoasOpenApi> content;

    private Pageable pageable;

    @Schema(example = "true")
    private Boolean last;

    @Schema(example = "4")
    private Integer totalPages;

    @Schema(example = "7")
    private Integer totalElements;

    @Schema(example = "1")
    private Integer numberOfElements;

    @Schema(example = "false")
    private Boolean first;

    @Schema(example = "2")
    private Integer size;

    @Schema(example = "3")
    private Integer number;

    private Pageable.Sort sort;

    @Schema(example = "false")
    private Boolean empty;



    @Schema(name = "Paginação")
    @Data
    public static class Pageable {

        @Schema(example = "3")
        private Integer pageNumber;

        @Schema(example = "2")
        private Integer pageSize;

        @Schema(example = "6")
        private Integer offset;

        @Schema(example = "true")
        private Boolean paged;

        @Schema(example = "false")
        private Boolean unpaged;

        private Sort sort;

        @Schema(name = "Ordenação")
        @Data
        public static class Sort {

            @Schema(example = "false")
            private Boolean sorted;

            @Schema(example = "true")
            private Boolean unsorted;

            @Schema(example = "true")
            private Boolean empty;
        }
    }
}
