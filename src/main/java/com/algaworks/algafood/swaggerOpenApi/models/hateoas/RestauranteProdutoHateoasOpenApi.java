package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(name = "Produto do Restaurante Output")
@Data
public class RestauranteProdutoHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Porco com molho agridoce")
    private String nome;

    @Schema(example = "Deliciosa carne suína ao molho especial")
    private String descricao;

    @Schema(example = "78.90")
    private BigDecimal preco;

    @Schema(example = "true")
    private Boolean ativo;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}