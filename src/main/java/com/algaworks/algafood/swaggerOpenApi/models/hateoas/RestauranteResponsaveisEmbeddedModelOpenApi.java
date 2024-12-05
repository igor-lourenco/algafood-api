package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.util.List;

@ApiModel("Responsáveis do restaurante Embedded")
@Data
public class RestauranteResponsaveisEmbeddedModelOpenApi {

    private List<RestauranteResponsaveisHateoasOpenApi> responsaveis;
}