package com.algaworks.algafood.api.DTOs;

import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//@ApiModel(value = "Pedido Resumido")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "pedidos") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de PedidoResumoDTO para pedidos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class PedidoResumoDTO extends RepresentationModel<PedidoResumoDTO> {

//    @ApiModelProperty(example = "ee13f455-c207-4be6-8eab-6c610567a9ef", position = 0)
    private String codigo;
//    @ApiModelProperty(example = "298.90", position = 5)
    private BigDecimal subtotal;
//    @ApiModelProperty(example = "10.90", position = 10)
    private BigDecimal taxaFrete;
//    @ApiModelProperty(example = "308.90", position = 15)
    private BigDecimal valorTotal;
//    @ApiModelProperty(example = "ENTREGUE", position = 20)
    private StatusPedido status;
//    @ApiModelProperty(example = "Thai Gourmet", position = 25)
    private String restaurante;
//    @ApiModelProperty(example = "Diana R", name = "campo apenas de exemplo para tratar a PropertyReferenceException quando o JPA fazer a ordenação da paginação", position = 30)
    private String nomeCliente; // campo apenas de exemplo para tratar a PropertyReferenceException quando o JPA fazer a ordenação da paginação
//    @ApiModelProperty(example = "Diana R", position = 35)
    private String cliente;

//    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 40)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

//    @ApiModelProperty(position = 45)
    private EnderecoDTO enderecoEntrega;
}
