package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.GruposPermissaoCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GrupoPermissaoHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados as Permissões dos Grupos.*/
@Tag(name = "Grupos")
@SecurityRequirement(name = "security_auth")
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



    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Associa permissão com grupo", responses = {
        @ApiResponse(responseCode= "204", description = "Associaçao realizada com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void associaPermissao(
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true) Long grupoId,
        @Parameter(name = "permissaoId", description = "ID da permissaoo", example = "1", required = true) Long permissaoId);



    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Desassocia permissão com grupo", responses = {
        @ApiResponse(responseCode= "204", description = "Desassociação realizada com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Grupo ou permissão não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void desassociaPermissao(
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true) Long grupoId,
        @Parameter(name = "permissaoId", description = "ID da permissao", example = "1", required = true) Long permissaoId);
}
