package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UsuarioNovaSenhaInput {

    @NotBlank
    private String senhaAtual;
    @NotBlank
    private String novaSenha;
}
