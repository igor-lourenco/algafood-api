package com.algaworks.algafood.api.DTOs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@ApiModel(value = "Vendas Diárias")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
//@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class VendaDiariaDTO {

    @ApiModelProperty(example = "05/09/2024", position = 0)
    private String data;

    @ApiModelProperty(example = "2", position = 5)
    private Long totalVendas;

    @ApiModelProperty(example = "468.90", position = 10)
    private BigDecimal totalFaturado;

}
