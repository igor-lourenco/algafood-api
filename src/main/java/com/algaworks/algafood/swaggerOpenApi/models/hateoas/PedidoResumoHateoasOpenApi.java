package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel("Objeto Pedido Resumo")
@Data
public class PedidoResumoHateoasOpenApi {

    @ApiModelProperty(example = "ee13f455-c207-4be6-8eab-6c610567a9ef", position = 0)
    private String codigo;
    @ApiModelProperty(example = "298.90", position = 5)
    private BigDecimal subtotal;
    @ApiModelProperty(example = "10.90", position = 10)
    private BigDecimal taxaFrete;
    @ApiModelProperty(example = "308.90", position = 15)
    private BigDecimal valorTotal;
    @ApiModelProperty(example = "ENTREGUE", position = 20)
    private StatusPedido status;
    @ApiModelProperty(example = "Thai Gourmet", position = 25)
    private String restaurante;
    @ApiModelProperty(example = "Diana R", name = "campo apenas de exemplo para tratar a PropertyReferenceException quando o JPA fazer a ordenação da paginação", position = 30)
    private String nomeCliente; // campo apenas de exemplo para tratar a PropertyReferenceException quando o JPA fazer a ordenação da paginação
    @ApiModelProperty(example = "Diana R", position = 35)
    private String cliente;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 40)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    @ApiModelProperty(position = 45)
    private EnderecoHateoasOpenApi enderecoEntrega;

    private Links _links;
}