package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.inputs.EstadoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.EstadosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.EstadoHateoasOpenApi;
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

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Estado.*/
@Tag(name = "Estados")
public interface EstadoControllerOpenApi {


    @Operation(summary = "Busca lista de todos os estados", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de estados encontrado com sucesso", content = @Content(schema = @Schema(implementation = EstadosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<EstadoDTO>> lista();



    @Operation(summary = "Busca estado pelo ID", responses = {
        @ApiResponse(responseCode= "200", description = "Estado encontrado com sucesso", content = @Content(schema = @Schema(implementation = EstadoHateoasOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<EstadoDTO> buscaPorId(@Parameter(name = "id", description = "ID da cidade", example = "1", schema = @Schema(type = "integer"),required = true) Long id);



    @Operation(summary = "Busca lista de estados pelo nome", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de estados encontrado", content = @Content(schema = @Schema(implementation = EstadosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<EstadoDTO>> buscaPorNome(@Parameter(name = "nome", description = "nome do estado", example = "Minas Gerais", required = true) String nome);



    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    @Operation(summary = "Cadastra um novo estado", responses = {
        @ApiResponse(responseCode= "201", description = "Estado cadastrado com sucesso", content = @Content(schema = @Schema(implementation = EstadoHateoasOpenApi.class))),
    })
    ResponseEntity<EstadoDTO> salva(@RequestBody(description = "Representação de uma novo estado", required = true) EstadoInput estadoInput);



    @Operation(summary = "Atualiza estado pelo ID", responses = {
        @ApiResponse(responseCode= "200", description = "Estado atualizado com sucesso", content = @Content(schema = @Schema(implementation = EstadoHateoasOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<EstadoDTO> altera(
        @Parameter(name = "id", description = "ID do estado", example = "1", schema = @Schema(type = "integer"), required = true) Long id,
        @RequestBody(description = "Representação do estado com os novos dados", required = true) EstadoInput estadoInput);



    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Exclui estado pelo ID", responses = {
        @ApiResponse(responseCode= "204", description = "Estado excluído com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Estado não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void deleta(@Parameter(name = "id", description = "ID do estado", example = "1",  schema = @Schema(type = "integer"), required = true) Long id);
}
