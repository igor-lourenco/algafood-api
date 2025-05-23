package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.FormasPagamentoCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.FormaPagamentoHateoasOpenApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Forma de pagamento. */
@Tag(name = "Formas de pagamento")
@SecurityRequirement(name = "security_auth")
public interface FormaPagamentoControllerOpenApi {


    @Operation(summary = "Busca lista de todas as formas de pagamento",
        parameters = @Parameter(
            name = "If-None-Match",
            description = "O servidor verifica se o conteúdo foi alterado com base nesse valor." +
                "<br>Se o cliente enviar este cabeçalho e não houver alterações no conteúdo, o servidor retornará um código de status 304 (Not Modified). " +
                "<br>Obs: O valor tem que estar entre aspas duplas.",
            example = " \"1741353832\" ",
            in = ParameterIn.HEADER),
        responses = {
        @ApiResponse(responseCode= "200", description = "Lista de formas de pagamento encontrado", content = @Content(schema = @Schema(implementation = FormasPagamentoCollectionModelOpenApi.class))),
        @ApiResponse(responseCode= "304", description = "Conteúdo não modificado (sem mudanças desde a última requisição)", content = @Content(schema = @Schema)),})
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> lista(ServletWebRequest request);



    @Operation(summary = "Busca forma pagamento pelo ID",
        parameters = @Parameter(
            name = "If-None-Match",
            description = "O servidor verifica se o conteúdo foi alterado com base nesse valor." +
                "<br>Se o cliente enviar este cabeçalho e não houver alterações no conteúdo, o servidor retornará um código de status 304 (Not Modified). " +
                "<br>Obs: O valor tem que estar entre aspas duplas.",
            example = " \"1741353832\" ",
            in = ParameterIn.HEADER),
        responses = {
            @ApiResponse(responseCode= "200", description = "Forma de pagamento encontrado com sucesso", content = @Content(schema = @Schema(implementation = FormaPagamentoHateoasOpenApi.class))),
            @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
            @ApiResponse(responseCode= "404", description = "Forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),})
    ResponseEntity<FormaPagamentoDTO> buscaPorId(
        @Parameter(name = "formaPagamentoId", description = "ID da forma de pagamento", example = "1", required = true) Long id, ServletWebRequest request);



    @Operation(summary = "Busca lista de formas de pagamento pelo nome", responses = {
            @ApiResponse(responseCode= "200", description = "Lista de formas de pagamento encontrado com sucesso", content = @Content(schema = @Schema(implementation = FormasPagamentoCollectionModelOpenApi.class))),
            @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),})
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> buscaPorNome(
        @Parameter(name = "nome", description = "Nome da forma de pagamento", example = "Cartão de crédito", required = true) String nome);



    @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
    @Operation(summary = "Cadastra uma nova forma de pagamento", responses = {
        @ApiResponse(responseCode= "201", description = "Forma de Pagamento cadastrado com sucesso", content = @Content(schema = @Schema(implementation = FormaPagamentoHateoasOpenApi.class))),})
    ResponseEntity<FormaPagamentoDTO> salva(
        @RequestBody(description = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);



    @Operation(summary = "Atualiza forma de pagamento pelo ID", responses = {
        @ApiResponse(responseCode= "200", description = "Forma de pagamento encontrado com sucesso", content = @Content(schema = @Schema(implementation = FormaPagamentoHateoasOpenApi.class))),
        @ApiResponse(responseCode= "404", description = "Forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class)))})
    ResponseEntity<FormaPagamentoDTO> altera(
        @Parameter(name = "formaPagamentoId", description = "ID da forma de pagamento", example = "1", required = true) Long id,
        @RequestBody(description = "Representação da forma de pagamento com os novos dados", required = true) FormaPagamentoInput formaPagamentoInput);



    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    @Operation(summary = "Exclui forma de pagamento pelo ID", responses = {
        @ApiResponse(responseCode= "204", description = "Forma de pagamento excluído com sucesso", content = @Content(schema = @Schema)),
        @ApiResponse(responseCode= "400", description = "Requisição inválida (erro do cliente)", content = @Content(schema = @Schema(implementation = StandardErrorBadRequest.class))),
        @ApiResponse(responseCode= "404", description = "Forma de pagamento não encontrado", content = @Content(schema = @Schema(implementation = StandardErrorNotFound.class))),
    })
    void deleta(@Parameter(name = "formaPagamentoId", description = "ID da forma de pagamento", example = "1", required = true) Long id);
}
