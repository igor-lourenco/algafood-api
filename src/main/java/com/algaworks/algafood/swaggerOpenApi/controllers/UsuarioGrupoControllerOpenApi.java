package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Usuários com grupo.*/
@Api(tags = "Usuarios")
public interface UsuarioGrupoControllerOpenApi {

    @ApiOperation(value = "Busca lista de grupos associados a um usuário")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de grupos do usuário encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class),})
    ResponseEntity<CollectionModel<UsuarioGrupoDTO>> lista(
        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId);


    @ApiOperation(value = "Associação do grupo com usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = StandardErrorNotFound.class),})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void associa(
        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId,
        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId);


    @ApiOperation(value = "Desassocia do grupo com usuário")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Usuário ou grupo não encontrado", response = StandardErrorNotFound.class),})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void desassocia(
        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId,
        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId);

}
