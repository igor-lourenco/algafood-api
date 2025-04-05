package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.swaggerOpenApi.models.UsuariosCollectionModelOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Usuarios.*/
@Tag(name = "Usuarios")
public interface UsuarioControllerOpenApi {


    @Operation(summary = "Busca lista de todos os usuários", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários encontrado com sucesso", content = @Content(schema = @Schema(implementation = UsuariosCollectionModelOpenApi.class)))
    })
    ResponseEntity<CollectionModel<UsuarioDTO>> lista();
//
//
//    @ApiOperation(value = "Busca usuário pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Usuário encontrada", response = UsuarioHateoasOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Usuário não encontrada", response = StandardErrorNotFound.class),})
//    ResponseEntity<UsuarioDTO> buscaPorId(@ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id);
//
//
//    @ApiOperation("Busca lista de usuários pelo nome")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de usuários encontrado", response = UsuariosCollectionModelOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    ResponseEntity<CollectionModel<UsuarioDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome do usuário", example = "Diana D", required = true) String nome);
//
//
//    @ApiOperation("Cadastra um novo usuário")
//    @ApiResponses({@ApiResponse(code = 201, message = "Usuário cadastrado", response = UsuarioHateoasOpenApi.class)})
//    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
//    ResponseEntity<UsuarioDTO> salva(@ApiParam(name = "payload", value = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuarioInput);
//
//
//    @ApiOperation("Atualiza usuário pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Usuário atualizado", response = UsuarioHateoasOpenApi.class),
//        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class)})
//    ResponseEntity<UsuarioDTO>altera(
//        @ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação do usuário com os novos dados", required = true) UsuarioInput usuarioInput);
//
//
//    @ApiOperation("Atualiza senha do usuário pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Senha do usuário atualizado"),
//        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void alteraSenha(
//        @ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação da nova senha do usuário", required = true) UsuarioNovaSenhaInput usuarioInput);
//
//
//    @ApiOperation(value = "Exclui usuário pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Usuário deletado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void deleta(@ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id);
}
