package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.CidadesCollectionModelOpenApiV2;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CidadeHateoasOpenApiV2;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cidade.*/
@Tag(name = "Cidades")
public interface CidadeControllerOpenApiV2 {


    @Operation(summary = "Busca lista de todas as cidades", responses ={
        @ApiResponse(responseCode = "200", description = "Lista de cidades encontrada", content = @Content(schema = @Schema(implementation = CidadesCollectionModelOpenApiV2.class)))
    })
    ResponseEntity<CollectionModel<CidadeDTOV2>> lista();



    @Operation(summary = "Busca cidade pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Cidade encontrada com sucesso", content = @Content(schema = @Schema(implementation = CidadeHateoasOpenApiV2.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CidadeDTOV2> buscaPorId(@Parameter(name = "id", description = "ID da cidade", example = "1", required = true) Long id);



    @Operation(summary = "Busca lista de cidades pelo nome", responses ={
        @ApiResponse(responseCode = "200", description = "Lista de cidades encontrada", content = @Content(schema = @Schema(implementation = CidadesCollectionModelOpenApiV2.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<CidadeDTOV2>> buscaPorNome(@Parameter(name = "nome", description = "nome da cidade", example = "São Paulo", required = true) String nome);
//
//
//    @ApiOperation("Cadastra uma nova cidade")
//    @ApiResponses(@ApiResponse(code = 201, message = "Cidade cadastrada", response = CidadeHateoasOpenApiV2.class))
//    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
//    ResponseEntity<CidadeDTOV2> salva(@ApiParam(name = "payload", value = "Representação de uma nova cidade", required = true) CidadeInputV2 cidadeInput);
//
//
//    @ApiOperation("Atualiza cidade pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Cidade atualizada", response = CidadeHateoasOpenApiV2.class),
//        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class)})
//    ResponseEntity<CidadeDTOV2> altera(
//        @ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação de uma nova cidade com os novos dados", required = true) CidadeInputV2 cidadeInput);
//
//
//    @ApiOperation(value = "Exclui cidade pelo ID" )
//
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Cidade deletada"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void deleta(@ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id);
}
