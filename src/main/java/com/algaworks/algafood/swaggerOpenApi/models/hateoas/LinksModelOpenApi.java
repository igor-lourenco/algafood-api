package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Schema(name = "Links Model")
@Getter
@Setter
public class LinksModelOpenApi {

    @Schema(name = "rel")
    private LinkModel rel;

    @Getter
    @Setter
    @Schema(name = "Rel Model")
    public static class LinkModel {

        @Schema(name = "href")
        private String href;

        @Schema(name = "templated")
        private boolean templated;
    }
}
