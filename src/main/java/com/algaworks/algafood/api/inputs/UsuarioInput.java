package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UsuarioInput {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
}