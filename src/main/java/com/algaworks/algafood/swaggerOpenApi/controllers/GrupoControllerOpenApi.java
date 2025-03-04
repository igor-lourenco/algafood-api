package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.GruposCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GrupoHateoasOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Grupo.*/
@Api(tags = "Grupos")
public interface GrupoControllerOpenApi {

    @ApiOperation(value = "Busca lista de todas os grupos")
    @ApiResponses(@ApiResponse(code = 200, message = "Lista dos grupos encontrado", response = GruposCollectionModelOpenApi.class))
    ResponseEntity<CollectionModel<GrupoDTO>> lista();

    @ApiOperation(value = "Busca grupo pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Grupo encontrado", response = GrupoHateoasOpenApi.class),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = StandardErrorNotFound.class),})
    ResponseEntity<GrupoDTO> buscaPorId(@ApiParam(name = "id", value = "ID do grupo", example = "1", required = true) Long id);


    @ApiOperation("Busca lista de grupos pelo nome")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de grupos encontrada", response = GruposCollectionModelOpenApi.class),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    ResponseEntity<CollectionModel<GrupoDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome do grupo", example = "Grupo 1", required = true) String nome);


    @ApiOperation("Cadastra uma novo grupo")
    @ApiResponses(@ApiResponse(code = 201, message = "Grupo cadastrado", response = GrupoHateoasOpenApi.class))
    @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<GrupoDTO> salva(@ApiParam(name = "payload", value = "Representação de um novo grupo", required = true) GrupoInput grupoInput);


    @ApiOperation("Atualiza grupo pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Grupo atualizado", response = GrupoHateoasOpenApi.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<GrupoDTO> altera(
        @ApiParam(name = "id", value = "ID do grupo", example = "1", required = true) Long id,
        @ApiParam(name = "payload", value = "Representação de um novo grupo com os novos dados", required = true) GrupoInput grupoInput);


    @ApiResponses({
        @ApiResponse(code = 204, message = "Grupo deletado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Grupo não encontrado", response = StandardErrorNotFound.class)})
    @ApiOperation(value = "Exclui grupo pelo ID")
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void deleta(@ApiParam(name = "id", value = "ID do grupo", example = "1", required = true) Long id);
}
