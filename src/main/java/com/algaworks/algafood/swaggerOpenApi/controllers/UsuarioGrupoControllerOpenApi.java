package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.UsuarioGruposCollectionModelOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Usuários com grupo.*/
@Tag(name = "Usuarios")
@SecurityRequirement(name = "security_auth")
public interface UsuarioGrupoControllerOpenApi {


    @Operation(summary = "Busca lista de grupos associados a um usuário", responses = {
        @ApiResponse(responseCode = "204", description = "Lista de grupos do usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioGruposCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CollectionModel<UsuarioGrupoDTO>> lista(
        @Parameter(name = "usuarioId", description = "ID do usuário", example = "1", required = true) Long usuarioId);



    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Associação do grupo com usuário", responses = {
        @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void associa(
        @Parameter(name = "usuarioId", description = "ID do usuário", example = "1", required = true) Long usuarioId,
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true) Long grupoId);



    @Operation(summary = "Desassocia do grupo com usuário", responses = {
        @ApiResponse(responseCode = "204", description = "Desassociação realizada com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Usuário ou grupo não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void desassocia(
        @Parameter(name = "usuarioId", description = "ID do usuário", example = "1", required = true) Long usuarioId,
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true) Long grupoId);

}
