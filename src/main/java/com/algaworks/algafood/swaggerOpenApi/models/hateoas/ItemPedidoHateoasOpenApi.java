package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

import java.math.BigDecimal;

@ApiModel("Item do Pedido Output")
@Data
public class ItemPedidoHateoasOpenApi {

    @ApiModelProperty(example = "1", position = 0)
    private Long id;
    @ApiModelProperty(example = "9.90", position = 5)
    private BigDecimal precoUnitario;
    @ApiModelProperty(example = "49.50", position = 10)
    private BigDecimal precoTotal;
    @ApiModelProperty(example = "5", position = 15)
    private Integer quantidade;
    @ApiModelProperty(example = "Menos picante, por favor", position = 20)
    private Long produtoId;
    @ApiModelProperty(example = "1", position = 25)
    private String observacao;
    @ApiModelProperty(example = "Pizza", position = 30)
    private String produtoNome;

    private Links _links;
}