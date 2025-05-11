package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "Usuário com senha Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class UsuarioComSenhaInput {

    @Schema(example = "Diana D", required = true)
    @NotBlank
    private String nome;

    @Schema(example = "diana@gmail.com", required = true)
    @NotBlank
    @Email
    private String email;

    @Schema(example = "123456", required = true)
    @NotBlank
    private String senha;
}
