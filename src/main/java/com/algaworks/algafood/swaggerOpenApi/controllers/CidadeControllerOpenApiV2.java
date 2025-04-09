package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.swaggerOpenApi.models.CidadesCollectionModelOpenApiV2;
import io.swagger.v3.oas.annotations.Operation;
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
//
//
//    @ApiOperation(value = "Busca cidade pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Cidade encontrada", response = CidadeHateoasOpenApiV2.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class),})
//    ResponseEntity<CidadeDTOV2> buscaPorId(@ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id);
//
//
//    @ApiOperation("Busca lista de cidades pelo nome")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de cidades encontrada", response = CidadesCollectionModelOpenApiV2.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    ResponseEntity<CollectionModel<CidadeDTOV2>> buscaPorNome(@ApiParam(name = "nome", value = "nome da cidade", example = "São Paulo", required = true) String nome);
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
