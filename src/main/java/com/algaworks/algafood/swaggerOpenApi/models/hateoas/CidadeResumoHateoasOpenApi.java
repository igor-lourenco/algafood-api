package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(name = "Cidade Resumida Output")
@Data
public class CidadeResumoHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Uberlândia")
    private String nome;

    @Schema(example = "Minas Gerais")
    private String estado;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}