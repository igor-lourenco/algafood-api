package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorBadRequest;
import com.algaworks.algafood.swaggerOpenApi.exceptions.StandardErrorNotFound;
import com.algaworks.algafood.swaggerOpenApi.models.FormasPagamentoCollectionModelOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.hateoas.FormaPagamentoHateoasOpenApi;
import io.swagger.annotations.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.ServletWebRequest;

/**Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Forma de pagamento.*/
@Api(tags = "Formas de pagamento")
public interface FormaPagamentoControllerOpenApi {

    @ApiOperation(value = "Busca lista de todas as formas de pagamento")
    @ApiResponses(@ApiResponse(code = 200, message = "Lista de formas de pagamento encontrado", response = FormasPagamentoCollectionModelOpenApi.class))
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> lista(ServletWebRequest request);

    @ApiOperation(value = "Busca forma pagamento pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Forma de pagamento encontrado", response = FormaPagamentoHateoasOpenApi.class),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Forma de pagamento não encontrado", response = StandardErrorNotFound.class),})
    ResponseEntity<FormaPagamentoDTO> buscaPorId(
        @ApiParam(name = "id", value = "ID da forma de pagamento", example = "1", required = true) Long id, ServletWebRequest request);


    @ApiOperation("Busca lista de formas de pagamento pelo nome")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de formas de pagamento encontrado", response = FormasPagamentoCollectionModelOpenApi.class),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    ResponseEntity<CollectionModel<FormaPagamentoDTO>> buscaPorNome(
        @ApiParam(name = "nome", value = "nome da forma de pagamento", example = "Cartão de crédito", required = true) String nome);


    @ApiOperation("Cadastra uma nova forma de pagamento")
    @ApiResponses(@ApiResponse(code = 201, message = "Forma de pagamento cadastrada", response = FormaPagamentoHateoasOpenApi.class))
    @ResponseStatus(value = HttpStatus.CREATED)// para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<FormaPagamentoDTO> salva(
        @ApiParam(name = "payload", value = "Representação de uma nova forma de pagamento", required = true) FormaPagamentoInput formaPagamentoInput);


    @ApiOperation("Atualiza forma de pagamento pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Forma de pagamento atualizado", response = FormaPagamentoHateoasOpenApi.class),
        @ApiResponse(code = 404, message = "Forma de pagamento não encontrado", response = StandardErrorNotFound.class)})
    ResponseEntity<FormaPagamentoDTO> altera(
        @ApiParam(name = "id", value = "ID da forma de pagamento", example = "1", required = true) Long id,
        @ApiParam(name = "payload", value = "Representação de uma nova forma de pagamento com os novos dados", required = true) FormaPagamentoInput formaPagamentoInput);


    @ApiResponses({
        @ApiResponse(code = 204, message = "Forma de pagamento deletada"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Forma de pagamento não encontrado", response = StandardErrorNotFound.class)})
    @ApiOperation(value = "Exclui forma de pagamento pelo ID")
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    void deleta(@ApiParam(name = "id", value = "ID da forma de pagamento", example = "1", required = true) Long id);
}
