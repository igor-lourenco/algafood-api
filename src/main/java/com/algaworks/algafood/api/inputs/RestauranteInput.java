package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Schema(name = "Restaurante Input")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class RestauranteInput {

    @Schema(example = "Restaurante Gourmet", required = true)
    @NotBlank
    private String nome;

    @Schema(example = "100", required = true)
    @DecimalMin(value = "0")
    @NotNull
    private BigDecimal precoFreteModelMapper;

//    @NotNull
//    @Valid // Força a validar as propriedades(atributos da classe) que estão com validação em CozinhaIdInput
//    private CozinhaIdInput cozinha;

    @Schema(example = "2", required = true)
    @NotNull
    private Long cozinhaId;

    @Schema(required = true)
    @NotNull
    @Valid
    private EnderecoInput endereco;

}
