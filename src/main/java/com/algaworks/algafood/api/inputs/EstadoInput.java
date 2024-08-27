package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EstadoInput {

    @NotNull
    private String nome;
}
