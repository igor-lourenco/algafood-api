package com.algaworks.algafood.domain.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/** Classe para usar como filtro passando os campos no @RequestParam da API - /pedidos/pesquisar em PedidoController*/
@Getter
@Setter
public class PedidoFilter {

    private Long clienteId;

    private Long restauranteId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoInicio;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoFim;
}
