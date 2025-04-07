package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.UsuarioGruposCollectionModelOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Usuários com grupo.*/
@Tag(name = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {


    @Operation(summary = "Busca lista de grupos associados a um usuário", responses = {
        @ApiResponse(responseCode = "204", description = "Lista de grupos do usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioGruposCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CollectionModel<UsuarioGrupoDTO>> lista(
        @Parameter(name = "usuarioId", description = "ID do usuário", example = "1", required = true) Long usuarioId);
//
//
//    @ApiOperation(value = "Associação do grupo com usuário")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = StandardErrorNotFound.class),})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void associa(
//        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId,
//        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId);
//
//
//    @ApiOperation(value = "Desassocia do grupo com usuário")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = StandardErrorNotFound.class),})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void desassocia(
//        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId,
//        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId);

}
