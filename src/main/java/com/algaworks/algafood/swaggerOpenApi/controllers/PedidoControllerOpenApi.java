package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.PedidoDTO;
import com.algaworks.algafood.api.DTOs.PedidoResumoDTO;
import com.algaworks.algafood.api.inputs.PedidoInput;
import com.algaworks.algafood.domain.filters.PedidoFilter;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.PedidoResumoFilterOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.PedidosCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.PedidoHateoasOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.PedidosPagedCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.pages.QueryParameter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
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
        @ApiResponse(responseCode= "201", description = "Pedido registrado com sucesso", content = @Content(schema = @Schema(implementation = PedidoHateoasOpenApi.class))),
    })
    ResponseEntity<PedidoDTO> salva(@RequestBody(description = "Representação de um novo Pedido", required = true) PedidoInput pedidoInput);



    @QueryParameter.Squiggly // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
    @Operation(summary = "Busca pedido pelo código", responses = {
        @ApiResponse(responseCode= "200", description = "Pedido encontrado com sucesso", content = @Content(schema = @Schema(implementation = PedidoHateoasOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Pedido não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))
    })
    ResponseEntity<PedidoDTO> buscaPeloCodigo(
        @Parameter(name = "codigoPedido", description = "Código do pedido", example = "ee13f455-c207-4be6-8eab-6c610567a9ef", required = true) String codigoPedido);



    @Operation(summary = "Busca lista de pedido filtrando os campos dinamicamente usando o @JsonFilter", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de pedidos encontrado com sucesso", content = @Content(array =
            @ArraySchema(minItems = 2, schema = @Schema(implementation = PedidoResumoFilterOpenApi.class))))})
    MappingJacksonValue listaPedidoComJsonFilter(
        @Parameter(name = "campos", description = "Nomes dos campos para filtrar na resposta, separados por vírgula", example = "codigo,valorTotal", required = true) String campos);



    @QueryParameter.PedidoFilter // Anotação criada para inserir os parâmetros de filtragem manualmente
    @Operation(summary = "Busca lista de pedidos utilizando os campos de uma classe passando como parâmetro e utilizando o Specification para consulta personalizada", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de pedidos encontrado com sucesso", content = @Content(schema = @Schema(implementation = PedidosCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    ResponseEntity<CollectionModel<PedidoResumoDTO>> pesquisa(@Parameter(hidden = true) PedidoFilter filtro);



    @QueryParameter.Pageable // anotação criada para inserir os parâmetros de paginação manualmente
    @QueryParameter.PedidoFilter // Anotação criada para inserir os parâmetros de filtragem manualmente
    @Operation(summary = "Busca paginação de pedidos utilizando os campos de uma classe passando como parâmetro e utilizando o Specification para consulta personalizada", responses = {
        @ApiResponse(responseCode= "200", description = "Lista de pedidos encontrado com sucesso", content = @Content(schema = @Schema(implementation = PedidosPagedCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
    })
    PagedModel<PedidoResumoDTO> pesquisaPage(@Parameter(hidden = true) PedidoFilter filtro, @Parameter(hidden = true) Pageable pageable);

}
