package com.algaworks.algafood.api.DTOs.jsonFilter;

import com.algaworks.algafood.api.DTOs.EnderecoDTO;
import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**  Essa classe foi criada como exemplo para ser usada com a annotation @JsonFilter da biblioteca Jackson, tem o objetivo de
 aplicar filtros dinâmicos durante a serialização de objetos JSON. Ela permite que em tempo de execução, quais serão os campos de
 um objeto serão incluídos ou excluídos no JSON resultante, sem precisar modificar a classe ou criar várias representações (DTOs). */
@Schema(name = "Pedido Json Filtro")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonFilter("pedidoFilter") // Indica que essa classe está sujeita ao filtro dinâmico identificado por "pedidoFilter".
public class PedidoResumoFilterDTO {

    @Schema(example = "ee13f455-c207-4be6-8eab-6c610567a9ef")
    private String codigo;

    @Schema(example = "298.90")
    private BigDecimal subtotal;

    @Schema(example = "10.90")
    private BigDecimal taxaFrete;

    @Schema(example = "308.90")
    private BigDecimal valorTotal;

    @Schema(example = "CRIADO")
    private StatusPedido status;

    @Schema(example = "Thai Gourmet")
    private String restaurante;

    @Schema(example = "Diana R")
    private String cliente;

    @Schema(example = "2024-09-05T14:04:11Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    private EnderecoDTO enderecoEntrega;
}
