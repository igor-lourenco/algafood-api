package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

//@ApiModel(value = "Estado Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class EstadoInput {

    @Schema(example = "Minas Gerais", required = true)
    @NotNull
    private String nome;
}
