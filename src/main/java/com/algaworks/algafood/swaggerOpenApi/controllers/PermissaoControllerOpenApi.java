package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.PermissaoDTO;
import com.algaworks.algafood.swaggerOpenApi.models.PermissoesCollectionModelOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Permissão.*/
@Tag(name = "Permissões")
@SecurityRequirement(name = "security_auth")
public interface PermissaoControllerOpenApi {


    @Operation(summary = "Busca lista de permissões", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de permissões encontrada com sucesso", content = @Content(schema = @Schema(implementation = PermissoesCollectionModelOpenApi.class))),
    })
    ResponseEntity<CollectionModel<PermissaoDTO>> lista();

}
