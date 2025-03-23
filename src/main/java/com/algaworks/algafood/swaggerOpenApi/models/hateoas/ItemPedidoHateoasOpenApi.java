package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(name = "Item do Pedido Output")
@Data
public class ItemPedidoHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "9.90")
    private BigDecimal precoUnitario;

    @Schema(example = "49.50")
    private BigDecimal precoTotal;

    @Schema(example = "5")
    private Integer quantidade;

    @Schema(example = "Menos picante, por favor")
    private Long produtoId;

    @Schema(example = "1")
    private String observacao;

    @Schema(example = "Pizza")
    private String produtoNome;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}