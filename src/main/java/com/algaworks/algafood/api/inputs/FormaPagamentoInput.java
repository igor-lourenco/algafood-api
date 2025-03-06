package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;

//@ApiModel(value = "Forma de Pagamento Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class FormaPagamentoInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Cartão de crédito", required = true)
    @NotBlank
    private String descricao;
}
