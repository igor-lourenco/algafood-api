package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ApiModel("Objeto Pedido")
@Data
public class PedidoHateoasOpenApi {

    @ApiModelProperty(example = "ee13f455-c207-4be6-8eab-6c610567a9ef", position = 0)
    private String codigo;
    @ApiModelProperty(example = "298.90", position = 5)
    private BigDecimal subtotal;
    @ApiModelProperty(example = "10.90", position = 10)
    private BigDecimal taxaFrete;
    @ApiModelProperty(example = "308.90", position = 15)
    private BigDecimal valorTotal;
    @ApiModelProperty(example = "CRIADO", position = 20)
    private StatusPedido status = StatusPedido.CRIADO;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 25)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 30)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataConfirmacao;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 35)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCancelamento;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 40)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataEntrega;

    @ApiModelProperty(position = 45)
    private FormaPagamentoHateoasOpenApi formaPagamento;
    @ApiModelProperty(position = 50)
    private RestauranteNomeHateoasOpenApi restaurante;
    @ApiModelProperty(position = 55)
    private UsuarioHateoasOpenApi cliente;
    @ApiModelProperty(position = 60)
    private EnderecoHateoasOpenApi enderecoEntrega;
    @ApiModelProperty(position = 65)
    private List<ItemPedidoHateoasOpenApi> itens = new ArrayList<>();

    private Links _links;
}