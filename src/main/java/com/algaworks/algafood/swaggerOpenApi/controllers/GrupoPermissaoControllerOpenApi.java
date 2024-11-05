package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados as Permissões dos Grupos.*/
@Api(tags = "Grupos")
public interface GrupoPermissaoControllerOpenApi {

    @ApiOperation(value = "Busca lista de permissões associados ao grupos")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de permissões do grupo encontrado"),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<List<GrupoPermissaoDTO>>  buscaTodasPermissoesDoGrupo(
        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId );


    @ApiOperation(value = "Busca a permissão associado ao grupo")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Permissão do grupo encontrado"),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<GrupoPermissaoDTO>  buscaPermissaoDoGrupoPeloId(
        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId,
        @ApiParam(name = "permissaoId", value = "ID da permissaoo", example = "1", required = true) Long permissaoId);



    @ApiOperation(value = "Associa permissão com grupo")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Associação realizada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> associaPermissao(
        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId,
        @ApiParam(name = "permissaoId", value = "ID da permissaoo", example = "1", required = true) Long permissaoId);


    @ApiOperation(value = "Desassocia permissão com grupo")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Desassociação realizada com sucesso"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Grupo ou permissão não encontrado", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> desassociaPermissao(
        @ApiParam(name = "grupoId", value = "ID do grupo", example = "1", required = true) Long grupoId,
        @ApiParam(name = "permissaoId", value = "ID da permissaoo", example = "1", required = true) Long permissaoId);
}
