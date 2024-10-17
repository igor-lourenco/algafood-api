package com.algaworks.algafood.api.inputs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "CidadeInput")
@Data
public class CidadeInput {

    @ApiModelProperty(example = "Uberlândia")
    @NotBlank
    private String nome;

    @NotNull
    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaIdInput
    private EstadoIdInput estado;
}
