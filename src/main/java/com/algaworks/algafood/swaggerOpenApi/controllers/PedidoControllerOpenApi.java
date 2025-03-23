package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.PedidosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PedidoHateoasOpenApi;
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

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Pedido.*/
@Tag(name = "Pedidos")
public interface PedidoControllerOpenApi {


    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca lista de todos os pedidos", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de pedidos encontrado com sucesso", content = @Content(schema = @Schema(implementation = PedidosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<PedidoResumoDTO>> lista();



    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    @Operation(summary = "Registra um novo pedido", responses = {
        @ApiResponse(responseCode= "200", description = "Pedido registrado com sucesso", content = @Content(schema = @Schema(implementation = PedidoHateoasOpenApi.class))),
    })
    ResponseEntity<PedidoDTO> salva(@RequestBody(description = "Representação de um novo Pedido", required = true) PedidoInput pedidoInput);



    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca pedido pelo código", responses = {
        @ApiResponse(responseCode= "200", description = "Pedido encontrado com sucesso", content = @Content(schema = @Schema(implementation = PedidoHateoasOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<PedidoDTO> buscaPeloCodigo(
        @Parameter(name = "codigoPedido", description = "Código do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true) String codigoPedido);
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
