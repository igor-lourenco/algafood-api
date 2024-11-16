package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.DTOs.jsonFilter.PedidoResumoFilterDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.filters.PedidoFilter;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import io.swagger.annotations.*;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Pedido.*/
@Api(tags = "Pedidos")
public interface PedidoControllerOpenApi {


    @ApiOperation(value = "Busca lista de todos os pedidos")
    @ApiResponses({ @ApiResponse(code = 200, message = "Lista de pedidos encontrado")})
    @ApiImplicitParams({ // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
        @ApiImplicitParam(
            value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "apenasOsCampos", paramType = "query", type = "string", example = "codigo,nome")
    })
    ResponseEntity<CollectionModel<PedidoResumoDTO>> lista();


    @ApiOperation("Registra um novo pedido")
    @ApiResponses({@ApiResponse(code = 201, message = "Pedido registrado")})
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<PedidoDTO> salva(@ApiParam(name = "payload", value = "Representação de um novo Pedido", required = true) PedidoInput pedidoInput);


    @ApiOperation(value = "Busca pedido pelo código")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Pedido encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Pedido não encontrado", response = StandardErrorNotFound.class),})
    @ApiImplicitParams({ // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
        @ApiImplicitParam(
            value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "apenasOsCampos", paramType = "query", type = "string", example = "codigo,status")})
    ResponseEntity<PedidoDTO> BuscaPeloCodigo(
        @ApiParam(name = "codigoPedido", value = "codigo do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true) String codigoPedido);


    @ApiOperation(value = "Busca lista de pedido filtrando os campos dinamicamente usando o @JsonFilter")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de pedidos encontrado.", response = PedidoResumoFilterDTO.class, responseContainer = "List")})
    MappingJacksonValue listaPedidoComJsonFilter(
        @ApiParam(name = "campos", value = "Nomes dos campos para filtrar na resposta, separados por vírgula", example = "codigo,valorTotal", required = true) String campos);


    @ApiOperation(value = "Busca lista de pedidos utilizando os campos de uma classe passando como parâmetro e utilizando o Specification para consulta personalizada")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Pedido encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    ResponseEntity<CollectionModel<PedidoResumoDTO>> pesquisa(PedidoFilter filtro);


    @ApiOperation(value = "Busca paginação de pedidos utilizando os campos de uma classe passando como parâmetro e utilizando o Specification para consulta personalizada")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Paginação de pedido encontrado"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    PagedModel<PedidoResumoDTO> pesquisaPage(PedidoFilter filtro, Pageable pageable);

}
