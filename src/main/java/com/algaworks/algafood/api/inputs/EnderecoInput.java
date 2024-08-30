package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EnderecoInput {

    @NotBlank
    private String cep;
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    private String complemento;
    @NotBlank
    private String bairro;

    @NotNull
    private Long cidadeId;

}
