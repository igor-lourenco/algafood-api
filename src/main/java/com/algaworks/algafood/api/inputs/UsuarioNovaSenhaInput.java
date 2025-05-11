package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Schema(name = "Usuário com nova senha Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class UsuarioNovaSenhaInput {

    @Schema(example = "123", required = true)
    @NotBlank
    private String senhaAtual;

    @Schema(example = "456", required = true)
    @NotBlank
    private String novaSenha;
}
