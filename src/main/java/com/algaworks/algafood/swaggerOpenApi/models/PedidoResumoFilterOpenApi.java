package com.algaworks.algafood.swaggerOpenApi.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/** Essa classe documenta o retorno da coleção de cidades que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@Schema(name = "Pedido Resumo Filter")
@Data
public class PedidoResumoFilterOpenApi {

    @Schema(example = "ee13f455-c207-4be6-8eab-6c610567a9ef")
    private String codigo;

    @Schema(example = "298.9")
    private BigDecimal valorTotal;
}
