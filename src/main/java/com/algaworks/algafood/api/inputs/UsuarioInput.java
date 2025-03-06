package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

//@ApiModel(value = "Usuário Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class UsuarioInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Diana D", required = true, position = 0)
    @NotBlank
    private String nome;

//    @ApiModelProperty(example = "diana@gmail.com", required = true, position = 5)
    @NotBlank
    @Email
    private String email;
}
