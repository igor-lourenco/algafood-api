package com.algaworks.algafood.swaggerOpenApi.models.pages;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhasEmbeddedModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de cozinhas com paginação customizada que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Cozinhas Paginadas Customizadas")
@Data
public class CozinhasPagedCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private CozinhasEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;

    private CozinhaPagedCollection page;

    @Schema(name = "Paginação Customizada")
    @Data
    public static class CozinhaPagedCollection {
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
