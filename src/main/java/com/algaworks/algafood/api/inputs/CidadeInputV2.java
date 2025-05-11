package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Cidade Input V2")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class CidadeInputV2 {

    @Schema(example = "Uberlândia")
    @NotBlank
    private String nome;

    @NotNull
    @Schema(example = "1")
    private Long estadoId;
}
