package com.algaworks.algafood.swaggerOpenApi.models.pages;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.LinksModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PedidosEmbeddedModelOpenApi;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/** Essa classe documenta o retorno da coleção de pedidos com paginação que implementa o hateoas, essa classe serve apenas para fins de documentação.*/
@Schema(name = "Pedidos Paginados Model")
@Data
public class PedidosPagedCollectionModelOpenApi {

    @Schema(name = "_embedded")
    private PedidosEmbeddedModelOpenApi _embedded;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;

    private PedidoPagedCollection page;

    @Schema(name = "Paginação de pedidos")
    @Data
    public static class PedidoPagedCollection {

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
