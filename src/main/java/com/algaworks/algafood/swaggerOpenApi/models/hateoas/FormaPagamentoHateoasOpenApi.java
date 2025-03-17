package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Forma Pagamento Output")
@Data
public class FormaPagamentoHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Cartão de crédito")
    private String descricao;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}