package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a status do Pedido.*/
@Tag(name = "Pedidos")
public interface PedidoStatusControllerOpenApi {


    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Altera status do pedido para 'CONFIRMADO'", responses = {
        @ApiResponse(responseCode = "204", description = "Status do pedido atualizado com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void confirmaPedido(
        @Parameter(name = "codigoPedido", description = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true)  String codigoPedido);



    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Altera status do pedido para 'ENTREGUE'", responses = {
        @ApiResponse(responseCode = "204", description = "Status do pedido atualizado com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void entregaPedido(
        @Parameter(name = "codigoPedido", description = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true)  String codigoPedido);



    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Altera status do pedido para 'CANCELADO'", responses = {
        @ApiResponse(responseCode = "204", description = "Status do pedido atualizado com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void cancelaPedido(
        @Parameter(name = "codigoPedido", description = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true)  String codigoPedido);

}
