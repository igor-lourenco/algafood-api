package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;

@Schema(name = "Forma de Pagamento Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class FormaPagamentoInput {

    @Schema(example = "Cartão de crédito")
    @NotBlank
    private String descricao;
}
