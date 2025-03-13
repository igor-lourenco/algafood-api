package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(name = "Cidade Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class CidadeInput {

    @Schema(example = "Uberlândia", required = true)
    @NotBlank
    private String nome;

    @NotNull
    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaIdInput
    private EstadoIdInput estado;
}
