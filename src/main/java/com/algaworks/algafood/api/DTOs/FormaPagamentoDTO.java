package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@ApiModel(value = "Forma de Pagamento") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "formas_pagamento") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de FormaPagamentoDTO para o formas_pagamento
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class FormaPagamentoDTO extends RepresentationModel<FormaPagamentoDTO> {

//  @ApiModelProperty(value = "ID da forma de pagamento", example = "1")
    @ApiModelProperty(example = "1", position = 0)
    private Long id;
    @ApiModelProperty(example = "Cartão de crédito", position = 5)
    private String descricao;
}
