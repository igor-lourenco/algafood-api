package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**
 * Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurante com as Formas de pagamento.
 */
@Api(tags = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {


    @ApiOperation(value = "Busca lista de usuários responsáveis associados ao restaurante")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de formas de pagamento do restaurante encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class),})
    ResponseEntity<List<RestauranteUsuarioDTO>> buscaUsuarioPeloRestaurante(
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId);


    @ApiOperation(value = "Associa restaurante com usuário responsável")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = StandardErrorNotFound.class),})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void associaUsuarioComRestaurante(
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId);


    @ApiOperation(value = "Desassocia restaurante do usuário responsável")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = StandardErrorNotFound.class),})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void desassociaUsuarioComRestaurante(
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId);

}
