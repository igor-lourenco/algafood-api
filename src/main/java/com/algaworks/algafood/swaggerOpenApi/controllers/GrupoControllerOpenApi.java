package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.GruposCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.GrupoHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Grupo.*/
//@Api(tags = "Grupos")
@Tag(name = "Grupos")
public interface GrupoControllerOpenApi {


    @Operation(summary = "Busca lista de todos os grupos", responses = {
        @ApiResponse(responseCode = "200", description = "Lista dos grupos encontrado", content = @Content(schema = @Schema(implementation = GruposCollectionModelOpenApi.class)))})
    ResponseEntity<CollectionModel<GrupoDTO>> lista();



    @Operation(summary = "Busca grupo pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Grupo encontrado com sucesso", content = @Content(schema = @Schema(implementation = GrupoHateoasOpenApi.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),})
    ResponseEntity<GrupoDTO> buscaPorId(@Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true, schema = @Schema(type = "integer")) Long id);



    @Operation(summary = "Busca lista de grupos pelo nome", responses = {
        @ApiResponse(responseCode = "200", description = "Lista de grupos encontrada com sucesso", content = @Content(schema = @Schema(implementation = GruposCollectionModelOpenApi.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class)))})
    ResponseEntity<CollectionModel<GrupoDTO>> buscaPorNome(@Parameter(name = "nome", description = "nome do grupo", example = "Grupo 1", required = true) String nome);



    @Operation(summary = "Cadastra um novo grupo", responses = {
        @ApiResponse(responseCode = "200", description = "Grupo cadastrado com sucesso", content = @Content(schema = @Schema(implementation = GrupoHateoasOpenApi.class))),})
    @ResponseStatus(value = HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<GrupoDTO> salva(@RequestBody(description = "Representação de um novo grupo", required = true) GrupoInput grupoInput);


    @Operation(summary = "Atualiza grupo pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Grupo atualizado com sucesso", content = @Content(schema = @Schema(implementation = GrupoHateoasOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Grupo não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))})
    ResponseEntity<GrupoDTO> altera(
        @Parameter(name = "grupoId", description = "ID do grupo", example = "1", required = true, schema = @Schema(type = "integer")) Long id,
        @RequestBody(description = "Representação do grupo com os novos dados", required = true) GrupoInput grupoInput);


//
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Grupo deletado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Grupo não encontrado", response = StandardErrorNotFound.class)})
//    @ApiOperation(value = "Exclui grupo pelo ID")
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void deleta(@ApiParam(name = "id", value = "ID do grupo", example = "1", required = true) Long id);
}
