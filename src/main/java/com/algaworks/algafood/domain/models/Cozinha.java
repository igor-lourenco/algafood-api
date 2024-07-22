package com.algaworks.algafood.domain.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@JsonRootName(value = "cozinha") // Customizando o XML como resposta
@Entity
@Table(name = "TB_COZINHA")
@Data
public class Cozinha implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty(value = "titulo") // Altera o atributo 'nome' para 'titulo' e retorna esse valor especificado como resposta
    @Column(name = "NOME")
    private String nome;

}
