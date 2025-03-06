package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Formas Pagamento Embedded")
@Data
public class FormasPagamentoEmbeddedModelOpenApi {

    private List<FormaPagamentoHateoasOpenApi> formas_pagamento;
}