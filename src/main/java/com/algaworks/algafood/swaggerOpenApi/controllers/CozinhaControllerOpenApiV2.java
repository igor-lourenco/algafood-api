package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedCollectionModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.pages.CozinhasPagedListModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;

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

//
//     @ApiOperation(value = "Busca lista de todas as cozinhas")
//     @ApiResponses(@ApiResponse(code = 200, message = "Lista de cozinhas encontrada", response = CozinhasCollectionModelOpenApiV2.class))
//     ResponseEntity<CollectionModel<CozinhaDTOV2>> lista();
//
//
//     @ApiOperation(value = "Busca cozinha pelo ID")
//     @ApiResponses({
//         @ApiResponse(code = 200, message = "Cozinha encontrada", response = CozinhaHateoasOpenApiV2.class),
//         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//         @ApiResponse(code = 404, message = "Cozinha não encontrado", response = StandardErrorNotFound.class),})
//     ResponseEntity<CozinhaDTOV2> buscaPorId(@ApiParam(name = "id", value = "ID da cozinha", example = "1", required = true) Long id);
//
//
//     @ApiOperation("Busca lista de cozinhas pelo nome")
//     @ApiResponses({
//         @ApiResponse(code = 200, message = "Lista de cozinhas encontrada", response = CozinhasCollectionModelOpenApiV2.class),
//         @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//     ResponseEntity<CollectionModel<CozinhaDTOV2>> buscaPorNome(@ApiParam(name = "nome", value = "nome da cozinha", example = "Brasileira", required = true) String nome);
//
//
//     @ApiOperation("Cadastra uma nova cozinha")
//     @ApiResponses(@ApiResponse(code = 201, message = "Cozinha cadastrada", response = CozinhaHateoasOpenApiV2.class))
//     @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
//     ResponseEntity<CozinhaDTOV2> salva(@ApiParam(name = "payload", value = "Representação de um nova cozinha", required = true) CozinhaInputV2 cozinhaInput);
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
