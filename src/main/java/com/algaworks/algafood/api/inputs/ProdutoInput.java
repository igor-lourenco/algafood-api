package com.algaworks.algafood.api.inputs;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Schema(name = "Produto Input") // Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class ProdutoInput {

    @Schema(example = "Porco com molho agridoce", required = true)
    @NotBlank
    private String nome;

    @Schema(example = "Deliciosa carne suína ao molho especial", required = true)
    @NotBlank
    private String descricao;

    @Schema(example = "78.90", required = true)
    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @Schema(example = "true")
    private Boolean ativo = true;
}
