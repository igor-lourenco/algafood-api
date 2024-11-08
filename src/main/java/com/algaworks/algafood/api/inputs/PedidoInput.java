package com.algaworks.algafood.api.inputs;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@ApiModel(value = "Pedido Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class PedidoInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
    @ApiModelProperty(example = "1", required = true, position = 0)
    @NotNull
    private Long restauranteId;


/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
    @ApiModelProperty(example = "3", required = true, position = 5)
    @NotNull
    private Long formaPagamentoId;


    @ApiModelProperty(position = 10)
    @Valid
    @NotNull
    private EnderecoInput enderecoEntrega;


    @ApiModelProperty(position = 15)
    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoInput> itens;
}
