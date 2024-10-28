package com.algaworks.algafood.api.inputs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@ApiModel(value = "Estado Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class EstadoInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
    @ApiModelProperty(example = "Minas Gerais", required = true)
    @NotNull
    private String nome;
}
