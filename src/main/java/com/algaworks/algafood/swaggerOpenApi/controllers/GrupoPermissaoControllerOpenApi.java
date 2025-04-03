package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.GruposPermissaoCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GrupoPermissaoHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados as Permissões dos Grupos.*/
@Tag(name = "Grupos")
public interface GrupoPermissaoControllerOpenApi {


    @Operation(summary = "Busca lista de permissões associados ao grupos", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de permissões do grupo encontradocom sucesso", content = @Content(schema = @Schema(implementation = GruposPermissaoCollectionModelOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CollectionModel<GrupoPermissaoDTO>> buscaTodasPermissoesDoGrupo(
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true) Long grupoId );



    @Operation(summary = "Busca a permissão associado ao grupo", responses = {
        @ApiResponse(responseCode= "200", description = "Permissão do grupo encontrado com sucesso", content = @Content(schema = @Schema(implementation = GrupoPermissaoHateoasOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<GrupoPermissaoDTO>  buscaPermissaoDoGrupoPeloId(
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true) Long grupoId,
        @Parameter(name = "permissaoId", description = "ID da permissaoo", example = "1", required = true) Long permissaoId);
//
//
//
//    @ApiOperation(value = "Associa permissão com grupo")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void associaPermissao(
//        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId,
//        @ApiParam(name = "permissaoId", value = "ID da permissaoo", example = "1", required = true) Long permissaoId);
//
//
//    @ApiOperation(value = "Desassocia permissão com grupo")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void desassociaPermissao(
//        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId,
//        @ApiParam(name = "permissaoId", value = "ID da permissao", example = "1", required = true) Long permissaoId);
}
