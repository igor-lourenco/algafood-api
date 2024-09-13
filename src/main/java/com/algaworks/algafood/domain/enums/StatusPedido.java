package com.algaworks.algafood.domain.enums;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum StatusPedido {
    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO, CONFIRMADO);

    private String descricao;
    private List<StatusPedido> contemStatusAnteriores;

    StatusPedido(String descricao, StatusPedido... contemStatusAnteriores) {
        this.descricao = descricao;
        this.contemStatusAnteriores = Arrays.asList(contemStatusAnteriores);
    }


    public boolean naoPodeAlterarPara(StatusPedido novoStatus){
        // se o novoStatus não conterStatusAnteriores no status atual(this) retorna true
        return !novoStatus.contemStatusAnteriores.contains(this);
    }
}