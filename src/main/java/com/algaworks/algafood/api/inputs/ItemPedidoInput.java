package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Schema(name = "Item do Pedido Input")
@Data
public class ItemPedidoInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long produtoId;


    @Schema(example = "5", required = true)
    @NotNull
    @Positive
    private Integer quantidade;

    @Schema(example = "Menos picante, por favor")
    private String observacao;
}
