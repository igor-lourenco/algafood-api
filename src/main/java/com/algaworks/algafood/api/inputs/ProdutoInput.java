package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

//@ApiModel(value = "Produto Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class ProdutoInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Porco com molho agridoce", required = true, position = 0)
    @NotBlank
    private String nome;

//    @ApiModelProperty(example = "Deliciosa carne suína ao molho especial", required = true, position = 5)
    @NotBlank
    private String descricao;

//    @ApiModelProperty(example = "78.90", required = true, position = 10)
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

//    @ApiModelProperty(example = "true", position = 15, hidden = true)
    private Boolean ativo = true;
}
