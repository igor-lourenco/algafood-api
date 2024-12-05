package com.algaworks.algafood.swaggerOpenApi.models;

import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteResponsaveisEmbeddedModelOpenApi;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

/** Essa classe documenta o retorno da coleção de resposáveis do restaurante que implementa o hateoas, essa classe serve apenas para fins de documentação. */
@ApiModel("Responsavéis do restaurante Collection")
@Data
public class RestauranteResponsaveisCollectionModelOpenApi {

    private RestauranteResponsaveisEmbeddedModelOpenApi _embedded;
    private Links _links;
}
