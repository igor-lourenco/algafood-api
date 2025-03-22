package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(name = "Pedidos Resumido Embedded")
@Data
public class PedidosEmbeddedModelOpenApi {

    private List<PedidoResumoHateoasOpenApi> pedidos;
}