package com.algaworks.algafood.api.DTOs.jsonFilter;

import com.algaworks.algafood.api.DTOs.EnderecoDTO;
import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**  Essa classe foi criada como exemplo para ser usada com a annotation @JsonFilter da biblioteca Jackson, tem o objetivo de
 aplicar filtros dinâmicos durante a serialização de objetos JSON. Ela permite que em tempo de execução, quais serão os campos de
 um objeto serão incluídos ou excluídos no JSON resultante, sem precisar modificar a classe ou criar várias representações (DTOs). */

@ApiModel(value = "PedidoJsonFilter")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonFilter("pedidoFilter") // Indica que essa classe está sujeita ao filtro dinâmico identificado por "pedidoFilter".
public class PedidoResumoFilterDTO {

    @ApiModelProperty(example = "ee13f455-c207-4be6-8eab-6c610567a9ef", position = 0)
    private String codigo;

    @ApiModelProperty(example = "298.90", position = 5)
    private BigDecimal subtotal;

    @ApiModelProperty(example = "10.90", position = 10)
    private BigDecimal taxaFrete;

    @ApiModelProperty(example = "308.90", position = 15)
    private BigDecimal valorTotal;

    @ApiModelProperty(example = "CRIADO", position = 20)
    private StatusPedido status;

    @ApiModelProperty(example = "Thai Gourmet", position = 25)
    private String restaurante;

    @ApiModelProperty(example = "Diana R", position = 30)
    private String cliente;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 35)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    @ApiModelProperty(position = 40)
    private EnderecoDTO enderecoEntrega;
}
