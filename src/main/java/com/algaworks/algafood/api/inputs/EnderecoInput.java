package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Endereco Input")
@Data
public class EnderecoInput {

    @Schema(example = "38400-000", required = true)
    @NotBlank
    private String cep;

    @Schema(example = "Rua Floriano Peixoto", required = true)
    @NotBlank
    private String logradouro;

    @Schema(example = "500", required = true)
    @NotBlank
    private String numero;

    @Schema(example = "Apto 801", required = true)
    private String complemento;

    @Schema(example = "Cazeca", required = true)
    @NotBlank
    private String bairro;

    @Schema(example = "2", required = true)
    @NotNull
    private Long cidadeId;

}
