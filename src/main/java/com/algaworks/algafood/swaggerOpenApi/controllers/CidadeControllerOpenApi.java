package com.algaworks.algafood.swaggerOpenApi.controllers;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cidade.*/
//@Api(tags = "Cidades")
@Tag(name = "Cidades")
public interface CidadeControllerOpenApi {

//    @ApiOperation(value = "Busca lista de todas as cidades", response = CidadesCollectionModelOpenApi.class)
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de cidades encontrada")})
//    ResponseEntity<CollectionModel<CidadeDTO>> lista();

    @Operation(summary = "Busca lista de todas as cidades")
     ResponseEntity<CollectionModel<CidadeDTO>> lista();
//
//
//    @ApiOperation(value = "Busca cidade pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Cidade encontrada", response = CidadeHateoasOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
//        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class),})
//    ResponseEntity<CidadeDTO> buscaPorId(@ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id);


    @Operation(summary = "Busca cidade pelo ID")
    ResponseEntity<CidadeDTO> buscaPorId(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id);
//
//
//    @ApiOperation("Busca lista de cidades pelo nome")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Lista de cidades encontrada", response = CidadesCollectionModelOpenApi.class),
//        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
//    ResponseEntity<CollectionModel<CidadeDTO>> buscaPorNome(@ApiParam(name = "nome", value = "nome da cidade", example = "São Paulo", required = true) String nome);
//
    @Operation(summary = "Busca lista de cidades pelo nome")
    ResponseEntity<CollectionModel<CidadeDTO>> buscaPorNome(@Parameter(description = "Nome de uma cidade", example = "São Paulo", required = true) String nome);
//
//    @ApiOperation("Cadastra uma nova cidade")
//    @ApiResponses(@ApiResponse(code = 201, message = "Cidade cadastrada", response = CidadeHateoasOpenApi.class))
//    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
//    ResponseEntity<CidadeDTO> salva(@ApiParam(name = "payload", value = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);
//
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    @Operation(summary = "Cadastra uma nova cidade", description = "Cadastro de uma cidade, necessita de um Estado e nome válido")
    ResponseEntity<CidadeDTO> salva(@RequestBody(description = "Representação de uma nova cidade", required = true) CidadeInput cidadeInput);
//
//    @ApiOperation("Atualiza cidade pelo ID")
//    @ApiResponses({
//        @ApiResponse(code = 200, message = "Cidade atualizada", response = CidadeHateoasOpenApi.class),
//        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class)})
//    ResponseEntity<CidadeDTO> altera(
//        @ApiParam(name = "id", value = "ID da cidade", example = "1", required = true) Long id,
//        @ApiParam(name = "payload", value = "Representação de uma nova cidade com os novos dados", required = true) CidadeInput cidadeInput);
//
    @Operation(summary = "Atualiza cidade pelo ID")
    ResponseEntity<CidadeDTO> altera(@Parameter(description = "ID de uma cidade", example = "1", required = true) Long id,
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
