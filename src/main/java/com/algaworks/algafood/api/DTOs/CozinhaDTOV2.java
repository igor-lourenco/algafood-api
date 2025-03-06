package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//@ApiModel(value = "Cozinha V2") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "cozinhas") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de CozinhaDTOV2 para cozinhas
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class CozinhaDTOV2 extends RepresentationModel<CozinhaDTOV2> {

//    @ApiModelProperty(example = "1")
    private Long cidadeId;
//    @ApiModelProperty(example = "Tailandesa")
    private String cidadeNome;
}
