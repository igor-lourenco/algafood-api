package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

//@ApiModel(value = "Item de Pedido")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "itens-pedido") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de ItemPedidoDTO para itens-pedido

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class ItemPedidoDTO extends RepresentationModel<ItemPedidoDTO> {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;

//    @ApiModelProperty(example = "9.90", position = 5)
    private BigDecimal precoUnitario;

//    @ApiModelProperty(example = "49.50", position = 10)
    private BigDecimal precoTotal;

//    @ApiModelProperty(example = "5", position = 15)
    private Integer quantidade;

//    @ApiModelProperty(example = "Menos picante, por favor", position = 20)
    private Long produtoId;

//    @ApiModelProperty(example = "1", position = 25)
    private String observacao;

//    @ApiModelProperty(example = "Pizza", position = 30)
    private String produtoNome;

}
