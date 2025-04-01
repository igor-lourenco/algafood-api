package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.RestauranteResponsaveisCollectionModelOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurante com as Formas de pagamento.
 */
@Tag(name = "Restaurantes")
public interface RestauranteUsuarioControllerOpenApi {


    @Operation(summary = "Busca lista de usuários responsáveis associados ao restaurante", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de responsáveis do restaurante encontrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteResponsaveisCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Restaurante não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CollectionModel<RestauranteUsuarioDTO>> buscaUsuarioPeloRestaurante(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId);



    @Operation(summary = "Associa restaurante com usuário responsável", responses = {
        @ApiResponse(responseCode = "204", description = "Associação realizada com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante ou usuário não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void associaUsuarioComRestaurante(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long restauranteId,
        @Parameter(name = "usuarioId", description = "ID do usuário", example = "1", required = true) Long usuarioId);
//
//
//    @ApiOperation(value = "Desassocia restaurante do usuário responsável")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante ou usuário não encontrado", response = StandardErrorNotFound.class),})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void desassociaUsuarioDoRestaurante(
//        @ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long restauranteId,
//        @ApiParam(name = "usuarioId", value = "ID do usuário", example = "1", required = true) Long usuarioId);

}
