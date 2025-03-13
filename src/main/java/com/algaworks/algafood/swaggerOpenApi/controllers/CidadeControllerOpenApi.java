package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorGone;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.CidadesCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.CidadeHateoasOpenApi;
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

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cidade.*/
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

    @Deprecated // Deprecia a API e o SpringDoc também mostra a API deprecidada na documentação
    @Operation(summary = "Busca lista de todas as cidades (Recurso depreciado)", responses ={
        @ApiResponse(responseCode = "410", description = "Recurso depreciado", content = @Content(schema = @Schema(implementation = StandardErrorGone.class)))
    })
    ResponseEntity<CollectionModel<CidadeDTO>> lista();



    @Operation(summary = "Busca cidade pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Cidade encontrada", content = @Content(schema = @Schema(implementation = CidadeHateoasOpenApi.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Cidade não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<CidadeDTO> buscaPorId(@Parameter(description = "ID de uma cidade", example = "1", required = true, schema = @Schema(type = "integer")) Long id);



    @Operation(summary = "Busca lista de cidades pelo nome", responses = {
        @ApiResponse(responseCode = "200", description = "Cidade encontrada", content = @Content(schema = @Schema(implementation = CidadesCollectionModelOpenApi.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<CidadeDTO>> buscaPorNome(@Parameter(description = "Nome de uma cidade", example = "São Paulo", required = true) String nome);



    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    @Operation(summary = "Cadastra uma nova cidade", description = "Cadastro de uma cidade, necessita de um Estado e nome válido", responses =
        @ApiResponse(responseCode = "200", description = "Cidade cadastrada com sucesso", content = @Content(schema = @Schema(implementation = CidadeHateoasOpenApi.class))))
    ResponseEntity<CidadeDTO> salva(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);



    @Operation(summary = "Atualiza cidade pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Cidade atualizada com sucesso", content = @Content(schema = @Schema(implementation = CidadeHateoasOpenApi.class)))})
    ResponseEntity<CidadeDTO> altera(@Parameter(description = "ID de uma cidade", example = "1", required = true, schema = @Schema(type = "integer")) Long id,
                                     @RequestBody(description = "Representação de uma cidade com os novos dados", required = true) CidadeInput cidadeInput);

    //
//    @ApiOperation(value = "Exclui cidade pelo ID" )
//
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Cidade deletada"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void deleta(@ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id);

    @Operation(summary = "Exclui cidade pelo ID")
    void deleta(@Parameter(description = "ID de uma cidade", example = "1", required = true)Long id);
}
