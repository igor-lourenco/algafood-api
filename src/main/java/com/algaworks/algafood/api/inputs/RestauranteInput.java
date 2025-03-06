package com.algaworks.algafood.api.inputs;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

//@ApiModel(value = "Restaurante Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class RestauranteInput {

/*  Mesmo usando a classe de configuração para adicionar os campos obrigatórios na documentação, quando o campo tem a anotação
    @ApiModelProperty a classe de configuração não consegue mapear corretamente porque essa anotação sobrescreve o valor parâmetro required */
//    @ApiModelProperty(example = "Restaurante Gourmet", required = true, position = 0)
    @NotBlank
    private String nome;

//    @ApiModelProperty(example = "100", required = true, position = 5 )
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal precoFreteModelMapper;

//    @NotNull
//    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaIdInput
//    private CozinhaIdInput cozinha;

//    @ApiModelProperty(example = "2", required = true, position = 10)
    @NotNull
    private Long cozinhaId;

//    @ApiModelProperty(required = true, position = 15)
    @NotNull
    @Valid
    private EnderecoInput endereco;

}
