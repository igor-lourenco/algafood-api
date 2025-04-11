package com.algaworks.algafood.swaggerOpenApi.models.pages;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhasEmbeddedModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de cozinhas com paginação que implementa o hateoas, essa classe serve apenas para fins de documentação.*/
@Schema(name = "Cozinhas Paginadas Model V2")
@Data
public class CozinhasPagedCollectionModelOpenApiV2 {

    @Schema(name = "_embedded")
    private CozinhasEmbeddedModelOpenApiV2 _embedded;

    private CozinhaPagedCollectionV2 page;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;

    @Schema(name = "Paginação V2")
    @Data
    private static class CozinhaPagedCollectionV2 {
        @Schema(example = "12", description = "Quantidade de registros por página")
        private Long size;
        @Schema(example = "50", description = "Total de registros")
        private Long totalElements;
        @Schema(example = "5", description = "Total de páginas")
        private Long totalPages;
        @Schema(example = "0", description = "Número da página (começa em 0)")
        private Long number;
    }
}
