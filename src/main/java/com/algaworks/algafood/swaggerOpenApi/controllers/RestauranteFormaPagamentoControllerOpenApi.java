package com.algaworks.algafood.swaggerOpenApi.controllers;

/**
 * Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurante com as Formas de pagamento.
 */
//@Api(tags = "Restaurantes")
public interface RestauranteFormaPagamentoControllerOpenApi {


//    @ApiOperation(value = "Busca lista de formas de pagamento do restaurante")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de formas de pagamento do restaurante encontrado", response = FormasPagamentoCollectionModelOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class),})
//    ResponseEntity<CollectionModel<FormaPagamentoDTO>> buscaFormaPagamentoPorRestauranteId(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId);
//
//
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
