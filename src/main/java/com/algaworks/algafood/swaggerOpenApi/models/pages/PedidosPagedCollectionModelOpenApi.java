package com.algaworks.algafood.swaggerOpenApi.models.pages;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PedidosEmbeddedModelOpenApi;
import lombok.Data;
import org.springframework.hateoas.Links;

/**
 * Essa classe documenta o retorno da coleção de pedidos com paginação que implementa o hateoas, essa classe serve apenas para fins de documentação.
 */
//@ApiModel("Pedidos Paginados Model")
@Data
public class PedidosPagedCollectionModelOpenApi {

    private PedidosEmbeddedModelOpenApi _embedded;
    private Links _links;
    private PedidoPagedCollection page;

//    @ApiModel("Paginação de pedidos")
    @Data
    private static class PedidoPagedCollection {
//        @ApiModelProperty(example = "12", value = "Quantidade de registros por página")
        private Long size;
//        @ApiModelProperty(example = "50", value = "Total de registros")
        private Long totalElements;
//        @ApiModelProperty(example = "5", value = "Total de páginas")
        private Long totalPages;
//        @ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
        private Long number;
    }
}
