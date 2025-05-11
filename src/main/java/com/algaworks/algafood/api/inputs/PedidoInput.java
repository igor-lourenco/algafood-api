package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;

@Schema(name = "Pedido Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class PedidoInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long restauranteId;

    @Schema(example = "3", required = true)
    @NotNull
    private Long formaPagamentoId;

//    @Schema(position = 10)
    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;

//    @Schema(position = 15)
    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoInput> itens;
}
