package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.FotoProdutoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteProdutoFotoHateoasOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Foto dos produtos do restaurante.*/
@Tag(name = "Produtos")
public interface RestauranteProdutoFotoControllerOpenApi {


    @Operation(summary = "Busca a foto do produto de um restaurante", hidden = true,  responses = {
        @ApiResponse(responseCode = "200", description = "Foto do produto de um restaurante encontrado", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<InputStreamResource> recuperaFoto(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @Parameter(name = "produtoId", description = "ID do produto", example = "1", required = true) Long produtoId,
        @Parameter(name = "accept", description = "produces", example = "image/png") String acceptHeader)
        throws HttpMediaTypeNotAcceptableException;



    @QueryParameter.Foto
    @Operation(summary = "Busca a foto do produto de um restaurante",  responses = {
        @ApiResponse(responseCode = "200", description = "Foto do produto de um restaurante encontrado",
            content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = RestauranteProdutoFotoHateoasOpenApi.class)),
                @Content(mediaType = "image/jpeg", schema = @Schema(type = "string", format = "binary"))
            }),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante ou produto não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<FotoProdutoDTO> recuperaDadosFoto(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @Parameter(name = "produtoId", description = "ID do produto", example = "1", required = true) Long produtoId);
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
