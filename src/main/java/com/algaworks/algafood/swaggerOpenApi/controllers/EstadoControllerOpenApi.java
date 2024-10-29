package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.inputs.EstadoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Estado.*/
@Api(tags = "Estados")
public interface EstadoControllerOpenApi {

    @ApiOperation(value = "Busca lista de todos os estados")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de estados encontrado")})
    ResponseEntity<List<EstadoDTO>> lista();


    @ApiOperation(value = "Busca estado pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Estado encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Estado não encontrada", response = StandardErrorNotFound.class),})
    ResponseEntity<EstadoDTO> buscaPorId(@ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id);


    @ApiOperation("Busca lista de estados pelo nome")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de estados encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    ResponseEntity<List<EstadoDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome do estado", example = "Minas Gerais", required = true) String nome);


    @ApiOperation("Cadastra um novo estado")
    @ApiResponses({@ApiResponse(code = 201, message = "Estado cadastrado")})
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<EstadoDTO> salva(@ApiParam(name = "payload", value = "Representação de uma novo estado", required = true) EstadoInput estadoInput);


    @ApiOperation("Atualiza estado pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Estado atualizado"),
        @ApiResponse(code = 404, message = "Estado não encontrado", response = StandardErrorNotFound.class)})
    public ResponseEntity<EstadoDTO> altera(
        @ApiParam(name = "id", value = "ID do estado", example = "1", required = true) Long id,
        @ApiParam(name = "payload", value = "Representação de um novo estado com os novos dados", required = true) EstadoInput estadoInput);


    @ApiOperation(value = "Exclui estado pelo ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Estado deletado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Estado não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> deleta(@ApiParam(name = "id", value = "ID do estado", example = "1", required = true) Long id);
}
