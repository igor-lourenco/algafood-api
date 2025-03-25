package com.algaworks.algafood.api.DTOs;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Relation(collectionRelation = "restaurantes") // Anotação para configurar o nome da lista que o hateoas vai representar na coleção de RestauranteDTO para restaurantes
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class RestauranteDTO extends RepresentationModel<RestauranteDTO> {

    private Long id;
    private String nome;
    private String taxaFrete;
    private CozinhaDTO cozinha;
    private Boolean ativo;
    private Boolean aberto;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private OffsetDateTime dataCadastro; // OffsetDateTime por padrão já usa o ISO 8601 UTC

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataAtualizacao;

    private EnderecoDTO endereco;
}
