package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Root Entry Point Output V2")
@Data
public class RootEntryPointHateoasOpenApiV2 {

    private Links _links;
}