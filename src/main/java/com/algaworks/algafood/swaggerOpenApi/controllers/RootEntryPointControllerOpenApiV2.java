package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RootEntryPointHateoasOpenApiV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints V2 relacionados a RootEntryPoint .*/
@Tag(name = "Root entry point")
public interface RootEntryPointControllerOpenApiV2 {


    @Operation(summary = "Busca os links para os recursos disponíveis da aplicação de forma dinâmicamente", responses = {
        @ApiResponse(responseCode= "200", description = "Links encontrados com sucesso", content = @Content(schema = @Schema(implementation = RootEntryPointHateoasOpenApiV2.class))),
    })
    ResponseEntity<RootEntryPointDTO> rootEntryPoint();

}
