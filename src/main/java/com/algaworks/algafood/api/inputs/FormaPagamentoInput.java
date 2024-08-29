package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class FormaPagamentoInput {

    @NotBlank
    private String descricao;
}
