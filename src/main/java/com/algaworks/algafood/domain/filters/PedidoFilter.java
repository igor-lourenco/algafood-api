package com.algaworks.algafood.domain.filters;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/** Classe para usar como filtro passando os campos no @RequestParam da API - /pedidos/pesquisar em PedidoController*/
@Getter
@Setter
//@ApiModel(value = "PedidoInputFilter")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
public class PedidoFilter {

//    @ApiModelProperty(example = "1", value = "ID do cliente", position = 0)
    private Long clienteId;

//    @ApiModelProperty(example = "1", value = "ID do restaurante", position = 5)
    private Long restauranteId;

//    @ApiModelProperty(example = "2024-09-17T08:48:56Z", value = "Data inicial", position = 10)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoInicio;

//    @ApiModelProperty(example = "2024-09-17T08:48:56Z", value = "Data final", position = 15)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dataCriacaoFim;
}
