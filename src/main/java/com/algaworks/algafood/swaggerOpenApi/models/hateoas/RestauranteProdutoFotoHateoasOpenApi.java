package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Objeto Foto do Produto do Restaurante")
@Data
public class RestauranteProdutoFotoHateoasOpenApi {

    @ApiModelProperty(example = "846a3fe8-14c2-400d-bd48-38a53c7716d3_pizza.jpg", position = 0)
    private String nomeArquivo;
    @ApiModelProperty(example = "Pizza", position = 5)
    private String descricao;
    @ApiModelProperty(example = "image/jpeg", position = 10)
    private String contentType;
    @ApiModelProperty(example = "14546", position = 15)
    private Long tamanho;

    private Links _links;
}