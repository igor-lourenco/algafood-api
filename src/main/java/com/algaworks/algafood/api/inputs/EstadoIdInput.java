package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

//@ApiModel(value = "Estado Id Input")
@Data
public class EstadoIdInput {

    @Schema(example = "1", required = true)
    @NotNull
    private Long id;
}
