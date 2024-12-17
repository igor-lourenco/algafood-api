package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.hateoas.Links;

@ApiModel("Root Entry Point Output")
@Data
public class RootEntryPointHateoasOpenApi {

    private Links _links;
}