package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Schema(name = "Vendas Diárias Output")// Usada no contexto do Swagger para descrever essa classe como modelo de dados que será utilizado na API
@Data
public class VendaDiariaHateoasOpenApi {

    @Schema(example = "05/09/2024")
    private String data;

    @Schema(example = "2")
    private Long totalVendas;

    @Schema(example = "468.90")
    private BigDecimal totalFaturado;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}