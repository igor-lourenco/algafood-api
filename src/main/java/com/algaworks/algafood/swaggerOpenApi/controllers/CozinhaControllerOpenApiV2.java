package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.api.inputs.CozinhaInputV2;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.CozinhasCollectionModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CozinhaHateoasOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedCollectionModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedListModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cozinha.*/
@Tag(name = "Cozinhas")
@SecurityRequirement(name = "security_auth")
public interface CozinhaControllerOpenApiV2 {


    @QueryParameter.Pageable // anotação criada para inserir os parâmetros de paginação manualmente
    @Operation(summary = "Busca lista de todas as cozinhas paginadas", responses = {
        @ApiResponse(responseCode = "200", description = "Paginação de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasPagedListModelOpenApi.class))),
    })
    Page<CozinhaDTOV2> listaPageable(@Parameter(hidden = true) Pageable pageable);



    @QueryParameter.Pageable // anotação criada para inserir os parâmetros de paginação manualmente
    @Operation(summary = "Busca lista de todas as cozinhas paginadas usando links do hateoas", responses = {
        @ApiResponse(responseCode = "200", description = "Paginação de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasPagedCollectionModelOpenApiV2.class))),
    })
     PagedModel<CozinhaDTOV2> listaPageableComLinks(@Parameter(hidden = true) Pageable pageable);



    @Operation(summary = "Busca lista de todas as cozinhas", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasCollectionModelOpenApiV2.class))),
    })
     ResponseEntity<CollectionModel<CozinhaDTOV2>> lista();



    @Operation(summary = "Busca cozinha pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApiV2.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Cozinha não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
     ResponseEntity<CozinhaDTOV2> buscaPorId(@Parameter(name = "id", description = "ID da cozinha", example = "1", required = true) Long id);



    @Operation(summary = "Busca lista de cozinhas pelo nome", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de cozinhas encontrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhasCollectionModelOpenApiV2.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<CozinhaDTOV2>> buscaPorNome(
        @Parameter(name = "nome", description = "nome da cozinha", example = "Brasileira", required = true) String nome);



    @Operation(summary = "Cadastra uma nova cozinha", responses = {
        @ApiResponse(responseCode = "201", description = "Cozinha cadastrada com sucesso", content = @Content(schema = @Schema(implementation = CozinhaHateoasOpenApiV2.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    @ResponseStatus(value = HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<CozinhaDTOV2> salva(@RequestBody(description = "Representação de um nova cozinha", required = true) CozinhaInputV2 cozinhaInput);
//
//
//     @ApiOperation("Atualiza cozinha pelo ID")
//     @ApiResponses({
//         @ApiResponse(code = 200, message = "Cozinha atualizada", response = CozinhaHateoasOpenApiV2.class),
//         @ApiResponse(code = 404, message = "Cozinha não encontrada", response = StandardErrorNotFound.class)})
//     ResponseEntity<CozinhaDTOV2> altera(
//         @ApiParam(name = "id", value = "ID da cozinha", example = "1", required = true) Long id,
//         @ApiParam(name = "payload", value = "Representação de uma nova cozinha com os novos dados", required = true) CozinhaInputV2 cozinhaInput);
//
//
//     @ApiResponses({
//         @ApiResponse(code = 204, message = "Cozinha deletada"),
//         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//         @ApiResponse(code = 404, message = "Cozinha não encontrada", response = StandardErrorNotFound.class)})
//     @ApiOperation(value = "Exclui Cozinha pelo ID")
//     @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//     void deleta(@ApiParam(name = "id", value = "ID da cozinha", example = "1", required = true) Long id);
}
