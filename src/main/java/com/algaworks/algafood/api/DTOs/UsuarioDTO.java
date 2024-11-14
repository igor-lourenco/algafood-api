package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@ApiModel(value = "Usuário")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Relation(collectionRelation = "usuarios") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de UsuarioDTO para o usuário
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class UsuarioDTO extends RepresentationModel<UsuarioDTO> {

    @ApiModelProperty(example = "1", position = 0)
    private Long id;

    @ApiModelProperty(example = "Diana R", position = 5)
    private String nome;

    @ApiModelProperty(example = "diana@gmail.com", position = 10)
    private String email;

    @ApiModelProperty(example = "2024-09-05T14:04:11Z", position = 15)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataCadastro;

}
