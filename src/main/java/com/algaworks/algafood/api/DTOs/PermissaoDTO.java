package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//@ApiModel(value = "Permissão") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "permissoes") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de PermissaoDTO para o permissoes
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class PermissaoDTO extends RepresentationModel<PermissaoDTO> {

//  @ApiModelProperty(value = "ID da permissao", example = "1")
//    @ApiModelProperty(example = "1", position = 0)
    private Long id;

//    @ApiModelProperty(example = "CONSULTAR_COZINHAS", position = 5)
    private String nome;

//    @ApiModelProperty(example = "Permite consultar cozinhas", position = 10)
    private String descricao;
}
