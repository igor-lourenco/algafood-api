package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Objeto Forma Pagamento")
@Data
public class FormaPagamentoHateoasOpenApi {

    @ApiModelProperty(example = "1", position = 0)
    private Long id;
    @ApiModelProperty(example = "Cartão de crédito", position = 5)
    private String descricao;

    private Links _links;
}