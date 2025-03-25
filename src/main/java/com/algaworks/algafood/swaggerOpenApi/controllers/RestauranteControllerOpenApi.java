package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.models.RestaurantesCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Restaurantes.*/
@Tag(name = "Restaurantes")
public interface RestauranteControllerOpenApi {


    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca lista de todos os restaurantes", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de restaurantes encontrado com sucesso", content = @Content(schema = @Schema(implementation = RestaurantesCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<RestauranteDTO>> lista();


//    @ApiOperation("Registra um novo restaurante")
//    @ApiResponses(@ApiResponse(code = 201, message = "Restaurante registrado", response = RestauranteHateoasOpenApi.class))
//    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
//    ResponseEntity<RestauranteDTO> salva(
//        @ApiParam(name = "payload", value = "Representação de um novo Restaurante", required = true) RestauranteInput restauranteInput);
//
//
//    @ApiOperation(value = "Busca restaurante pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Restaurante encontrado", response = RestauranteHateoasOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class),})
//    @ApiImplicitParams({ // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
//        @ApiImplicitParam(
//            value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
//            name = "apenasOsCampos", paramType = "query", type = "string", example = "id,nome")})
//    ResponseEntity<RestauranteDTO> buscaPorId(
//        @ApiParam(name = "id", value = "ID do restaurante", example = "1", required = true) Long id);
//
//
//    @ApiOperation("Atualiza restaurante pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Restaurante atualizado", response = RestauranteHateoasOpenApi.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    ResponseEntity<RestauranteDTO> altera(
//        @ApiParam(name = "id", value = "ID do restaurante", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação do restaurante com os novos dados", required = true) RestauranteInput restauranteInput);
//
//
//    @ApiOperation(value = "Exclui restaurante pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurante deletada"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void deleta(@ApiParam(name = "id", value = "ID do restaurante", example = "1", required = true) Long id);
//
//
//    @ApiOperation(value = "Atualiza restaurante pelo ID", hidden = true) // O hidden = true permite ocultar a documentação dessa API, para ser documentada pela API abaixo alteraParcialSwagger()
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Restaurante atualizado", response = RestauranteHateoasOpenApi.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    ResponseEntity<RestauranteDTO> alteraParcial(
//        @ApiParam(name = "id", value = "ID do restaurante", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação do restaurante com os novos dados") Map<String, Object> campos,
//        HttpServletRequest request);
//
//
//    /** Essa API foi criada apenas para visualização customizada na documentação simulando a API - PATCH altera parcialmente Restaurante*/
//    @ApiOperation(value = "Atualiza restaurante parcialmente pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Restaurante atualizado", response = RestauranteHateoasOpenApi.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 500, message = "Erro interno no servidor", response = StandardErrorInternalServerError.class),})
//    ResponseEntity<RestauranteDTO> alteraParcialSwagger(
//        @ApiParam(name = "id", value = "ID do restaurante", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação do restaurante apenas com os campos que devem ser atualizados", required = true)
//        RestauranteParcialModelOpenApi restauranteInput);
//
//
//    @ApiOperation(value = "Altera o status do restaurante para aberto (aberto = true)")
//    @ApiResponses({
//        @ApiResponse(code = 204, message = "Restaurante aberto"),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Restaurante não encontrado", response = StandardErrorNotFound.class)})
//    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
//    void abertura(@ApiParam(name = "restauranteId", value = "ID do restaurante", example = "1", required = true) Long id);
//
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
