package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;

//@ApiModel(value = "Produto") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "produtos") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de ProdutoDTO para o produtos
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class ProdutoDTO  extends RepresentationModel<ProdutoDTO> {

//   @ApiModelProperty(value = "ID do produto", example = "1")
//    @ApiModelProperty(example = "1", position = 0)
    private Long id;

//    @ApiModelProperty(example = "Porco com molho agridoce", position = 5)
    private String nome;

//    @ApiModelProperty(example = "Deliciosa carne suína ao molho especial", position = 10)
    private String descricao;

//    @ApiModelProperty(example = "78.90", position = 15)
    private BigDecimal preco;

//    @ApiModelProperty(example = "true", position = 20)
    private Boolean ativo;

}
