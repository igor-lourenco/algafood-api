package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.UsuariosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.UsuarioHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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



    @Operation(summary = "Busca usuário pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso", content = @Content(schema = @Schema(implementation = UsuarioHateoasOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Usuário não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<UsuarioDTO> buscaPorId(@Parameter(name = "id", description = "ID do usuário", example = "1", required = true) Long id);



    @Operation(summary = "Busca lista de usuários pelo nome", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de usuários encontrado com sucesso", content = @Content(schema = @Schema(implementation = UsuariosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<UsuarioDTO>> buscaPorNome(@Parameter(name = "nome", description = "Nome do usuário", example = "Diana D", required = true) String nome);
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
