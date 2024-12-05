package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RootEntryPointHateoasOpenApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a RootEntryPoint .*/
@Api(tags = "Root entry point")
public interface RootEntryPointControllerOpenApi {

    @ApiOperation(value = "Busca os links para os recursos disponíveis da aplicação de forma dinâmicamente")
    @ApiResponses(
        @ApiResponse(code = 200, message = "Links encontrados com sucesso", response = RootEntryPointHateoasOpenApi.class))
    ResponseEntity<RootEntryPointDTO> rootEntryPoint();

}
