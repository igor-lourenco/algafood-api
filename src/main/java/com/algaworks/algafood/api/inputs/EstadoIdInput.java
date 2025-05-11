package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Schema(name = "Estado Id Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class EstadoIdInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long id;
}
