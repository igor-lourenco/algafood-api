package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Pedidos Resumido Embedded")
@Data
public class PedidosEmbeddedModelOpenApi {

    private List<PedidoResumoHateoasOpenApi> pedidos;
}