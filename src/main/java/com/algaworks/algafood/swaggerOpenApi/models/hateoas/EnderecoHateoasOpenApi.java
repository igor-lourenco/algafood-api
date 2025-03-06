package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

//@ApiModel("Endereco Output")
@Data
public class EnderecoHateoasOpenApi {

//    @ApiModelProperty(example = "38400-000", position = 0)
    private String cep;
//    @ApiModelProperty(example = "Rua Floriano Peixoto", position = 5)
    private String logradouro;
//    @ApiModelProperty(example = "500", position = 10)
    private String numero;
//    @ApiModelProperty(example = "Apto 801", position = 15)
    private String complemento;
//    @ApiModelProperty(example = "Cazeca", position = 20)
    private String bairro;

//    @ApiModelProperty(position = 25)
    private CidadeResumoHateoasOpenApi cidade;
}