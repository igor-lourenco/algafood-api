package com.algaworks.algafood.api.DTOs;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@ApiModel(value = "Endereco")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true) // Permite que crie um builder a partir de uma instância existente, o que pode ser útil em casos onde você precisa modificar ou complementar um objeto sem criar um novo do zero.
@JsonInclude(JsonInclude.Include.NON_NULL) // Ignora campos com valores nulos durante a serialização para JSON
public class EnderecoDTO {

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
    private CidadeResumoDTO cidade;
}
