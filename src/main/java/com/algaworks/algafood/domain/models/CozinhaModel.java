package com.algaworks.algafood.domain.models;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@JsonRootName(value = "cozinha") // Customizando o XML como resposta
@Entity
@Table(name = "TB_COZINHA")
@Data
public class CozinhaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @JsonProperty(value = "titulo") // Altera o atributo 'nome' para 'titulo' e retorna esse valor especificado como resposta
    @Column(name = "NOME")
    private String nome;

}
