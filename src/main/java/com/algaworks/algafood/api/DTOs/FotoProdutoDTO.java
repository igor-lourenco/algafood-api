package com.algaworks.algafood.api.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//@ApiModel(value = "Foto do Produto") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "fotos") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de FotoProdutoDTO para o fotos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
//@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class FotoProdutoDTO extends RepresentationModel<FotoProdutoDTO> {

//    @ApiModelProperty(example = "846a3fe8-14c2-400d-bd48-38a53c7716d3_pizza.jpg", position = 0)
    private String nomeArquivo;

//    @ApiModelProperty(example = "Pizza", position = 5)
    private String descricao;

//    @ApiModelProperty(example = "image/jpeg", position = 10)
    private String contentType;

//    @ApiModelProperty(example = "14546", position = 15)
    private Long tamanho;
}
