package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.FormasPagamentoCollectionModelOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurante com as Formas de pagamento. */
@Tag(name = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {


    @Operation(summary = "Busca lista de formas de pagamento do restaurante", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de formas de pagamento do restaurante encontrado", content = @Content(schema = @Schema(implementation = FormasPagamentoCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> buscaFormaPagamentoPorRestauranteId(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId);


//    @ApiOperation(value = "Associa forma de pagamento com restaurante")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = StandardErrorNotFound.class),})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void associaFormaPagamentoDoRestaurante(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "formaPagamentoId", value = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);
//
//
//    @ApiOperation(value = "Desassocia forma de pagamento do restaurante")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante ou forma de pagamento não encontrado", response = StandardErrorNotFound.class),})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void desassociaFormaPagamentoDoRestaurante(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "formaPagamentoId", value = "ID da forma de pagamento", example = "1", required = true) Long formaPagamentoId);

}
