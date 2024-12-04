package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.math.BigDecimal;

@ApiModel("Objeto Produto do Restaurante")
@Data
public class RestauranteProdutoHateoasOpenApi {

    @ApiModelProperty(example = "1", position = 0)
    private Long id;
    @ApiModelProperty(example = "Porco com molho agridoce", position = 5)
    private String nome;
    @ApiModelProperty(example = "Deliciosa carne suína ao molho especial", position = 10)
    private String descricao;
    @ApiModelProperty(example = "78.90", position = 15)
    private BigDecimal preco;
    @ApiModelProperty(example = "true", position = 20)
    private Boolean ativo;

    private Links _links;
}