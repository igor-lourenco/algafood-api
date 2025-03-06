package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@ApiModel(value = "Cidade Input V2")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class CidadeInputV2 {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Uberlândia", required = true)
    @NotBlank
    private String nome;

    @NotNull
//    @ApiModelProperty(example = "1", required = true)
    private Long estadoId;
}
