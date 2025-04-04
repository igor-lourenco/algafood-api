package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Foto do Produto Output")
@Data
public class RestauranteProdutoFotoHateoasOpenApi {

    @Schema(example = "846a3fe8-14c2-400d-bd48-38a53c7716d3_pizza.jpg")
    private String nomeArquivo;

    @Schema(example = "Pizza")
    private String descricao;

    @Schema(example = "image/jpeg")
    private String contentType;

    @Schema(example = "14546")
    private Long tamanho;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}