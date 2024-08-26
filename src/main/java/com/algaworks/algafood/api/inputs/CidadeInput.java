package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CidadeInput {

    @NotBlank
    private String nome;

    @NotNull
    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaIdInput
    private EstadoIdInput estado;
}
