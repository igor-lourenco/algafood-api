package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import lombok.Getter;
import lombok.Setter;

//@ApiModel("Links")
@Getter
@Setter
public class LinksModelOpenApi {

    private LinkModel rel;

    @Getter
    @Setter
    private static class LinkModel{
        private String href;
        private boolean templated;
    }
}
