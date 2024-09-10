package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
public class PedidoInput {

    @NotNull
    private Long restauranteId;

    @NotNull
    private Long formaPagamentoId;

    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoInput> itens;
}
