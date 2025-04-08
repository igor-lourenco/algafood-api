package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Root Entry Point Output")
@Data
public class RootEntryPointHateoasOpenApi {

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}