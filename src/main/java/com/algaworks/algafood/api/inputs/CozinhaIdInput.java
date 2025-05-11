package com.algaworks.algafood.api.inputs;

import lombok.Data;

import jakarta.validation.constraints.NotNull;

@Data
public class CozinhaIdInput {

    @NotNull
    private Long id;
}
