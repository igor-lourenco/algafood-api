package com.algaworks.algafood.swaggerOpenApi.models.pages;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhasEmbeddedModelOpenApi;
import lombok.Data;
import org.springframework.hateoas.Links;

/**
 * Essa classe documenta o retorno da coleção de cozinhas com paginação que implementa o hateoas, essa classe serve apenas para fins de documentação.
 */
//@ApiModel("Cozinhas Paginadas Model")
@Data
public class CozinhasPagedCollectionModelOpenApi {

    private CozinhasEmbeddedModelOpenApi _embedded;
    private Links _links;
    private CozinhaPagedCollection page;

//    @ApiModel("Paginação de cozinhas")
    @Data
    private static class CozinhaPagedCollection {
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
