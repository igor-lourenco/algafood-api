package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GrupoInput {

    @NotBlank
    private String nome;
}
