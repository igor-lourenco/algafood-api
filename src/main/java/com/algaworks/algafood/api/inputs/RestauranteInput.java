package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class RestauranteInput {

    @NotBlank
    private String nome;

    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal precoFreteModelMapper;

//    @NotNull
//    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaIdInput
//    private CozinhaIdInput cozinha;

    @NotNull
    private Long cozinhaId;

    @NotNull
    @Valid
    private EnderecoInput endereco;

}
