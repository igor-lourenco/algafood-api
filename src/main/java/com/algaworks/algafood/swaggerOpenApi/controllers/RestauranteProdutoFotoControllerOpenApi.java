package com.algaworks.algafood.swaggerOpenApi.controllers;

/**
 * Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Foto dos produtos do restaurante.
 */
//@Api(tags = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {


//    @ApiOperation(value = "Busca a foto do produto de um restaurante", hidden = true)
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de produtos encontrado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class),})
//    ResponseEntity<InputStreamResource> recuperaFoto(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId,
//        @ApiParam(name = "accept", value = "produces", example = "image/png") String acceptHeader)
//        throws HttpMediaTypeNotAcceptableException;
//
//
//    @ApiOperation(value = "Busca a foto do produto de um restaurante", produces = "application/json, image/jpeg, image/png")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Foto do produto de um restaurante encontrado", response = RestauranteProdutoFotoHateoasOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante, produto ou foto não encontrado", response = StandardErrorNotFound.class),})
//    ResponseEntity<FotoProdutoDTO> recuperaDadosFoto(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId);
//
//
//    @ApiOperation(value = "Atualiza a foto do produto de um restaurante")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Foto do produto atualizada", response = RestauranteProdutoFotoHateoasOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = StandardErrorNotFound.class),})
//    ResponseEntity<FotoProdutoDTO> atualizaFoto(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId,
//        FotoProdutoInput fotoProdutoInput,
//        @ApiParam(name = "arquivo", value = "arquivo da foto do produto (máximo 100KB, apenas JPG e PNG)", required = true) MultipartFile arquivo);
//
//
//    @ApiOperation(value = "Exclui a foto do produto de um restaurante")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Foto do produto de um restaurante deletado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante, produto ou foto não encontrado", response = StandardErrorNotFound.class),})
//    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void deletaFoto(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId);
//
//
//    @ApiOperation(value = "API de teste para salvar foto do produto", hidden = true)
//    void atualizarFotoTeste(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId,
//        @ApiParam(name = "fotoProdutoInput", value = "dados da foto do produto", required = true) FotoProdutoInput fotoProdutoInput) throws IOException;
}
