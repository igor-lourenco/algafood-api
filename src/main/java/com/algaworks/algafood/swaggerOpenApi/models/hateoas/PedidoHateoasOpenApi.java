package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Schema(name = "Pedido Output")
@Data
public class PedidoHateoasOpenApi {

    @Schema(example = "ee13f455-c207-4be6-8eab-6c610567a9ef")
    private String codigo;
    @Schema(example = "298.90")
    private BigDecimal subtotal;
    @Schema(example = "10.90")
    private BigDecimal taxaFrete;
    @Schema(example = "308.90")
    private BigDecimal valorTotal;
    @Schema(example = "CRIADO")
    private StatusPedido status = StatusPedido.CRIADO;

    @Schema(example = "2024-09-05T14:04:11Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    @Schema(example = "null")
    private String dataConfirmacao;

    @Schema(example = "null")
    private String dataCancelamento;

    @Schema(example = "null")
    private String dataEntrega;

    private FormaPagamentoHateoasOpenApi formaPagamento;

    private RestauranteNomeHateoasOpenApi restaurante;

    private UsuarioHateoasOpenApi cliente;

    private EnderecoHateoasOpenApi enderecoEntrega;

    private List<ItemPedidoHateoasOpenApi> itens = new ArrayList<>();

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}