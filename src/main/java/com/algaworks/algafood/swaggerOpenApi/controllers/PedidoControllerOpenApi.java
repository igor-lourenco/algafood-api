package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.models.PedidosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Pedido.*/
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {


    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca lista de todos os pedidos", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de pedidos encontrado com sucesso", content = @Content(schema = @Schema(implementation = PedidosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<PedidoResumoDTO>> lista();
//
//
//    @ApiOperation("Registra um novo pedido")
//    @ApiResponses({@ApiResponse(code = 201, message = "Pedido registrado", response = PedidoHateoasOpenApi.class)})
//    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
//    ResponseEntity<PedidoDTO> salva(@ApiParam(name = "payload", value = "Representação de um novo Pedido", required = true) PedidoInput pedidoInput);
//
//
//    @ApiOperation(value = "Busca pedido pelo código")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Pedido encontrado", response = PedidoHateoasOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Pedido não encontrado", response = StandardErrorNotFound.class),})
//    @ApiImplicitParams({ // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
//        @ApiImplicitParam(
//            value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
//            name = "apenasOsCampos", paramType = "query", type = "string", example = "codigo,status")})
//    ResponseEntity<PedidoDTO> buscaPeloCodigo(
//        @ApiParam(name = "codigoPedido", value = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true) String codigoPedido);
//
//
//    @ApiOperation(value = "Busca lista de pedido filtrando os campos dinamicamente usando o @JsonFilter")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de pedidos encontrado.", response = PedidoResumoFilterDTO.class, responseContainer = "List")})
//    MappingJacksonValue listaPedidoComJsonFilter(
//        @ApiParam(name = "campos", value = "Nomes dos campos para filtrar na resposta, separados por vírgula", example = "codigo,valorTotal", required = true) String campos);
//
//
//    @ApiOperation(value = "Busca lista de pedidos utilizando os campos de uma classe passando como parâmetro e utilizando o Specification para consulta personalizada")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Pedido encontrado", response = PedidosCollectionModelOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    ResponseEntity<CollectionModel<PedidoResumoDTO>> pesquisa(PedidoFilter filtro);
//
//
//    @ApiOperation(value = "Busca paginação de pedidos utilizando os campos de uma classe passando como parâmetro e utilizando o Specification para consulta personalizada")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Paginação de pedido encontrado", response = PedidosPagedCollectionModelOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    PagedModel<PedidoResumoDTO> pesquisaPage(PedidoFilter filtro, Pageable pageable);

}
