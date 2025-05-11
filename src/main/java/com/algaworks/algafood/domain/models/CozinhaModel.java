package com.algaworks.algafood.domain.models;

import com.algaworks.algafood.core.constraints.groups.Groups;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonRootName(value = "cozinha") // Customizando o XML como resposta
@Entity
@Table(name = "TB_COZINHA")
@Data
public class CozinhaModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {Groups.CadastroRestaurante.class})
    private Long id;


//  @JsonProperty(value = "titulo") // Altera o atributo 'nome' para 'titulo' e retorna esse valor especificado como resposta
    @Column(name = "NOME")
    @NotBlank
    private String nome;


    @JsonIgnore
    @OneToMany(mappedBy = "cozinha")  // Mapeado pela chave estrangeira que está declarada na classe RestauranteModel
    private List<RestauranteModel> restaurantes = new ArrayList<>();
}
