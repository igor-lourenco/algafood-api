package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Usuarios.*/
@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

    @ApiOperation(value = "Busca lista de todas os usuários")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de usuários encontrado")})
    ResponseEntity<List<UsuarioDTO>> lista();


    @ApiOperation(value = "Busca usuário pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário encontrada"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Usuário não encontrada", response = StandardErrorNotFound.class),})
    ResponseEntity<UsuarioDTO> buscaPorId(@ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id);


    @ApiOperation("Busca lista de usuários pelo nome")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de usuários encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    ResponseEntity<List<UsuarioDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome do usuário", example = "Diana D", required = true) String nome);


    @ApiOperation("Cadastra um novo usuário")
    @ApiResponses({@ApiResponse(code = 201, message = "Usuário cadastrado")})
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<UsuarioDTO> salva(@ApiParam(name = "payload", value = "Representação de um novo usuário", required = true) UsuarioComSenhaInput usuarioInput);


    @ApiOperation("Atualiza usuário pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<UsuarioDTO>altera(
        @ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id,
        @ApiParam(name = "payload", value = "Representação do usuário com os novos dados", required = true) UsuarioInput usuarioInput);


    @ApiOperation("Atualiza senha do usuário pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Senha do usuário atualizado"),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<UsuarioDTO>alteraSenha(
        @ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id,
        @ApiParam(name = "payload", value = "Representação da nova senha do usuário", required = true) UsuarioNovaSenhaInput usuarioInput);


    @ApiOperation(value = "Exclui usuário pelo ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Usuário deletado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Usuário não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void deleta(@ApiParam(name = "id", value = "ID do usuário", example = "1", required = true) Long id);
}
