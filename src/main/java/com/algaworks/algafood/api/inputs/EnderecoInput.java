package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//@ApiModel(value = "Endereco Input")
@Data
public class EnderecoInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "38400-000", required = true, position = 0)
    @NotBlank
    private String cep;


/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Rua Floriano Peixoto", required = true, position = 5)
    @NotBlank
    private String logradouro;


/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "500", required = true, position = 10)
    @NotBlank
    private String numero;


//    @ApiModelProperty(example = "Apto 801", required = true, position = 15)
    private String complemento;


/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Cazeca", required = true, position = 20)
    @NotBlank
    private String bairro;

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "2", required = true, position = 25)
    @NotNull
    private Long cidadeId;

}
