package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;
import org.springframework.hateoas.Links;

//@ApiModel("Forma Pagamento Output")
@Data
public class FormaPagamentoHateoasOpenApi {

//    @ApiModelProperty(example = "1", position = 0)
    private Long id;
//    @ApiModelProperty(example = "Cartão de crédito", position = 5)
    private String descricao;

    private Links _links;
}