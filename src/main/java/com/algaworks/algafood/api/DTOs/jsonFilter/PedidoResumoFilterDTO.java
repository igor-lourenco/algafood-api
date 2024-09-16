package com.algaworks.algafood.api.DTOs.jsonFilter;

import com.algaworks.algafood.api.DTOs.EnderecoDTO;
import com.algaworks.algafood.domain.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**  Essa classe foi criada como exemplo para ser usada com a annotation @JsonFilter da biblioteca Jackson, tem o objetivo de
 aplicar filtros dinâmicos durante a serialização de objetos JSON. Ela permite que em tempo de execução, quais serão os campos de
 um objeto serão incluídos ou excluídos no JSON resultante, sem precisar modificar a classe ou criar várias representações (DTOs). */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonFilter("pedidoFilter") // Indica que essa classe está sujeita ao filtro dinâmico identificado por "pedidoFilter".
public class PedidoResumoFilterDTO {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private StatusPedido status;
    private String restaurante;
    private String cliente;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private LocalDateTime dataCriacao;

    private EnderecoDTO enderecoEntrega;
}
