package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class ItemPedidoInput {

    @NotNull
    private Long produtoId;
    @NotNull
    @Positive
    private Integer quantidade;
    private String observacao;
}
