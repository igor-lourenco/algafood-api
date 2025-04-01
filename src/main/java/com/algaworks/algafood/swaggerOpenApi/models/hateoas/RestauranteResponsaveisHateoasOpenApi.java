package com.algaworks.algafood.swaggerOpenApi.models.hateoas;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(name = "Responsável do restaurante Output")
@Data
public class RestauranteResponsaveisHateoasOpenApi {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Diana R")
    private String nome;

    @Schema(example = "diana@gmail.com")
    private String email;

    @Schema(example = "2024-09-05T14:04:11Z")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'") // Padrão ISO 8601 UTC
    private LocalDateTime dataCadastro;

    @Schema(name = "_links")
    private LinksModelOpenApi _links;
}