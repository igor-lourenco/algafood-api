package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Data;

import java.util.List;

//@ApiModel("Responsáveis do restaurante Embedded")
@Data
public class RestauranteResponsaveisEmbeddedModelOpenApi {

    private List<RestauranteResponsaveisHateoasOpenApi> responsaveis;
}