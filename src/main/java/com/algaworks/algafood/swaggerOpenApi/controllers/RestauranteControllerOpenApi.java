package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorMediaTypeNotSupported;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardInternalServerError;
import com.algaworks.algafood.swaggerOpenApi.models.RestauranteParcialModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.RestaurantesCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.RestauranteHateoasOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurantes.*/
@Tag(name = "Restaurantes")
public interface RestauranteControllerOpenApi {


    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca lista de todos os restaurantes", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de restaurantes encontrado com sucesso", content = @Content(schema = @Schema(implementation = RestaurantesCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<RestauranteDTO>> lista();



    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    @Operation(summary = "Registra um novo restaurante", responses = {
        @ApiResponse(responseCode= "201", description = "Restaurante cadastrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteHateoasOpenApi.class))),
    })
    ResponseEntity<RestauranteDTO> salva(
        @RequestBody(description = "Representação de um novo Restaurante", required = true) RestauranteInput restauranteInput);



    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca restaurante pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Restaurante encontrado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteHateoasOpenApi.class))),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<RestauranteDTO> buscaPorId(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long id);



    @Operation(summary = "Atualiza restaurante pelo ID", responses = {
        @ApiResponse(responseCode = "200", description = "Restaurante atualizado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteHateoasOpenApi.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<RestauranteDTO> altera(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long id,
        @RequestBody(description = "Representação do restaurante com os novos dados", required = true) RestauranteInput restauranteInput);



    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Exclui restaurante pelo ID", responses = {
        @ApiResponse(responseCode = "204", description = "Restaurante deletado com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void deleta(@Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long id);



    @Operation(summary = "Atualiza restaurante parcialmente pelo ID",
        requestBody = @RequestBody(description = "Representação do restaurante com os novos dados",
            required = true,
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = RestauranteParcialModelOpenApi.class))
        ),
        responses = {
            @ApiResponse(responseCode = "200", description = "Restaurante parcialmente atualizado com sucesso", content = @Content(schema = @Schema(implementation = RestauranteHateoasOpenApi.class))),
            @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
            @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),
            @ApiResponse(responseCode = "406", description = "Recurso não possui representação que poderia ser aceita pelo consumidor, ajuste o header Accept", content = @Content(schema = @Schema)),
            @ApiResponse(responseCode = "415", description = "Requisição recusada porque o corpo está em um formato não suportado, ajuste o header Content-Type", content = @Content(schema = @Schema(implementation = StandardErrorMediaTypeNotSupported.class))),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(schema = @Schema(implementation = StandardInternalServerError.class)))
    })
    ResponseEntity<RestauranteDTO> alteraParcial(
        @Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long id,
        @RequestBody(description = "Representação do restaurante com os novos dados") Map<String, Object> campos,
        HttpServletRequest request);



    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Altera o status do restaurante para aberto (aberto = true)", responses = {
        @ApiResponse(responseCode = "204", description = "Restaurante aberto com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode = "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode = "404", description = "Restaurante não encontrada", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    void abertura(@Parameter(name = "restauranteId", description = "ID do restaurante", example = "1", required = true) Long id);


//
//    @ApiOperation(value = "Altera o status do restaurante para ativado (ativo = true)")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurante ativado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void ativa(@ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long id);
//
//
//    @ApiOperation(value = "Altera o status do restaurante para fechado (aberto = false)")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurante fechado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void fechamento(@ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long id);
//
//
//    @ApiOperation(value = "Altera o status do restaurante para desativado (ativo = false)")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurante desativado"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void inativa(@ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long id);
//
//
//    @ApiOperation(value = "Altera o status de uma lista de restaurantes para ativado (ativo = true)")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurantes ativados"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void ativacoes(@ApiParam(name = "restauranteIds", value = "ID dos restaurantes", required = true) List<Long> restauranteIds);
//
//
//    @ApiOperation("Busca lista de restaurantes pelo nome que tem frete grátis (taxaFrete = 0.00)")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de restaurantes encontrado", response = RestaurantesCollectionModelOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    ResponseEntity<CollectionModel<RestauranteDTO>> buscaRestaurantesComFreteGratis(
//        @ApiParam(name = "nome", value = "nome do restaurante", example = "Th") String nome);
//
//
//    @ApiOperation("Busca lista de restaurantes com o json resumido usando o @JsonView para projeção dos campos retornados")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de restaurantes encontrado", response = RestauranteViewDTO.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    ResponseEntity<List<RestauranteViewDTO>> listarComJsonView();
//
//
//    @ApiOperation("Busca lista de restaurantes usando o @JsonView para projeção dos campos retornados dinâmicamente")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de restaurantes encontrado", response = RestauranteNomeDTO.class, responseContainer = "List"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    MappingJacksonValue listaComWrapper(
//        @ApiParam(
//            name = "projecao",
//            allowableValues = "apenas-nome, resumo",
//            value = "Define a projeção desejada para o retorno. Se não passar nenhum parâmetro, o retorno será todos os campos por padrão. Valores permitidos:\n" +
//                "  - apenas-nome: Retorna apenas os campos id e nome dos restaurantes.\n" +
//                "  - resumo: Retorna uma visão resumida com campos adicionais.\n") String projecao);
//
//
//    @ApiOperation(value = "Altera o status de uma lista de restaurantes para inativado (ativo = false)")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurantes inativados"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void inativacoes(@ApiParam(name = "restauranteIds", value = "ID dos restaurantes", required = true) List<Long> restauranteIds);
}
