package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a status do Pedido.*/
@Api(tags = "Pedidos")
public interface PedidoStatusControllerOpenApi {


    @ApiOperation(value = "Altera status do pedido para 'CONFIRMADO'")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Atualizado status do pedido"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> confirmaPedido(
        @ApiParam(name = "codigoPedido", value = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true)  String codigoPedido);


    @ApiOperation(value = "Altera status do pedido para 'ENTREGUE'")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Atualizado status do pedido"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> entregaPedido(
        @ApiParam(name = "codigoPedido", value = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true)  String codigoPedido);


    @ApiOperation(value = "Altera status do pedido para 'CANCELADO'")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Atualizado status do pedido"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> cancelaPedido(
        @ApiParam(name = "codigoPedido", value = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true)  String codigoPedido);

}
