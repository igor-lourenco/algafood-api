package com.algaworks.algafood.api.inputs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "EstadoIdInput")
@Data
public class EstadoIdInput {

    @ApiModelProperty(example = "1")
    @NotNull
    private Long id;
}
