package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(name = "Pedido Resumo Output")
@Data
public class PedidoResumoHateoasOpenApi {

    @Schema(example = "ee13f455-c207-4be6-8eab-6c610567a9ef")
    private String codigo;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.90")
    private BigDecimal taxaFrete;

    @Schema(example = "308.90")
    private BigDecimal valorTotal;

    @Schema(example = "ENTREGUE")
    private StatusPedido status;

    @Schema(example = "Thai Gourmet")
    private String restaurante;

    @Schema(example = "Diana R", name = "nomeCliente")
    private String nomeCliente; // campo apenas de exemplo para tratar a PropertyReferenceException quando o JPA fazer a ordenação da paginação

    @Schema(example = "Diana R")
    private String cliente;

    @Schema(example = "2024-09-05T14:04:11Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    @Schema(name = "enderecoEntrega")
    private EnderecoHateoasOpenApi enderecoEntrega;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}