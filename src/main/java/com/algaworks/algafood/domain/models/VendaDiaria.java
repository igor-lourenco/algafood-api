package com.algaworks.algafood.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@Setter
public class VendaDiaria {

    private String data;
    private Long totalVendas;
    private BigDecimal totalFaturado;
}

