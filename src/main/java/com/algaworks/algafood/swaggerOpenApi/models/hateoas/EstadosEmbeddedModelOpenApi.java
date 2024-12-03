package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Estados Embedded")
@Data
public class EstadosEmbeddedModelOpenApi {

    private List<EstadoHateoasOpenApi> estados;
}