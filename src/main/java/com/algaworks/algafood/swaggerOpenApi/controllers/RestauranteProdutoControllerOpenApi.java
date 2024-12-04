package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.RestauranteProdutosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteProdutoHateoasOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurante com os produtos.
 */
@Api(tags = "Produtos")
public interface RestauranteProdutoControllerOpenApi {


    @ApiOperation(value = "Busca lista dos produtos de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de produtos encontrado", response = RestauranteProdutosCollectionModelOpenApi.class),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class),})
    ResponseEntity<CollectionModel<ProdutoDTO>> buscaTodosProdutosDoRestaurante(
        @ApiParam(name = "incluirInativos", value = "boolean", example = "true") Boolean incluirInativos,
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId);


    @ApiOperation(value = "Busca produto de um restaurante pelo id do produto")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Produtos encontrado", response = RestauranteProdutoHateoasOpenApi.class),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = StandardErrorNotFound.class),})
    ResponseEntity<ProdutoDTO> buscaProdutoPeloId(
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId);


    @ApiOperation("Cadastra produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 201, message = "Produto registrado", response = RestauranteProdutoHateoasOpenApi.class),
        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<ProdutoDTO> salvaProdutoNoRestaurante(
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(name = "payload", value = "Representação de um novo produto", required = true) ProdutoInput produtoInput);


    @ApiOperation("Atualiza produto de um restaurante")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Produto atualizado", response = RestauranteProdutoHateoasOpenApi.class),
        @ApiResponse(code = 404, message = "Restaurante ou produto não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<ProdutoDTO> alteraProdutoDoRestaurante(
        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @ApiParam(name = "produtoId", value = "ID do produto", example = "1", required = true) Long produtoId,
        @ApiParam(name = "payload", value = "Representação do produto com os novos campos", required = true) ProdutoInput produtoInput);
}
