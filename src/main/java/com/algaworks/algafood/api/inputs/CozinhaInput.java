package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Schema(name = "Cozinha Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class CozinhaInput {

    @Schema(example = "Tailandesa")
    @NotBlank
    private String nome;
}
