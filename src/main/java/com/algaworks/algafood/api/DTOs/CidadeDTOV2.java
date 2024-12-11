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

@ApiModel(value = "Cidade V2") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "cidades") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de CidadeDTO para o usuário
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class CidadeDTOV2 extends RepresentationModel<CidadeDTOV2> {

//  @ApiModelProperty(value = "ID da cidade", example = "1")
    @ApiModelProperty(example = "1", position = 0)
    private Long id;

    @ApiModelProperty(example = "Uberlândia", position = 5)
    private String nome;

//    @ApiModelProperty(position = 10)
//    private EstadoDTO estado;
}
