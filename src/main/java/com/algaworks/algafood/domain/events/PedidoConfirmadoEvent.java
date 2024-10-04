package com.algaworks.algafood.domain.events;

import com.algaworks.algafood.domain.models.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoConfirmadoEvent {

    private PedidoModel pedidoModel;
}
