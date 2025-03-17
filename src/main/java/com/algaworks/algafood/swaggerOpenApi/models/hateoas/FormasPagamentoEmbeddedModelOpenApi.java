package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Formas Pagamento Embedded")
@Data
public class FormasPagamentoEmbeddedModelOpenApi {

    private List<FormaPagamentoHateoasOpenApi> formas_pagamento;
}