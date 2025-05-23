package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.RestauranteProdutosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteProdutoHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurante com os produtos.*/
@Tag(name = "Produtos")
@SecurityRequirement(name = "security_auth")
public interface RestauranteProdutoControllerOpenApi {


    @Operation(summary = "Busca lista dos produtos de um restaurante", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de produtos encontrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteProdutosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CollectionModel<ProdutoDTO>> buscaTodosProdutosDoRestaurante(
        @Parameter(name = "incluirInativos", description = "boolean", example = "true") Boolean incluirInativos,
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId);



    @Operation(summary = "Busca produto de um restaurante pelo id do produto", responses = {
        @ApiResponse(responseCode= "200", description = "Produto encontrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteProdutoHateoasOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Restaurante ou produto não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<ProdutoDTO> buscaProdutoPeloId(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @Parameter(name = "produtoId", description = "ID do produto", example = "1", required = true) Long produtoId);



    @Operation(summary = "Cadastra produto de um restaurante", responses = {
        @ApiResponse(responseCode = "201", description = "Produto registrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteProdutoHateoasOpenApi.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<ProdutoDTO> salvaProdutoNoRestaurante(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @RequestBody(description = "Representação de um novo produto", required = true) ProdutoInput produtoInput);



    @Operation(summary = "Atualiza produto de um restaurante", responses = {
        @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteProdutoHateoasOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<ProdutoDTO> alteraProdutoDoRestaurante(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @Parameter(name = "produtoId", description = "ID do produto", example = "1", required = true) Long produtoId,
        @RequestBody(description = "Representação do produto com os novos campos", required = true) ProdutoInput produtoInput);
}
