package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Root Entry Point Output V2")
@Data
public class RootEntryPointHateoasOpenApiV2 {

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}