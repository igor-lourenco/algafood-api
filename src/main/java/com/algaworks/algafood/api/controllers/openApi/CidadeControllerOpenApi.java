package com.algaworks.algafood.api.controllers.openApi;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.controllers.exceptions.openApi.StandardErrorBadRequest;
import com.algaworks.algafood.api.controllers.exceptions.openApi.StandardErrorNotFound;
import com.algaworks.algafood.api.inputs.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

/** Essa interface é usada para gerar a documentação da API e definir os contratos dos endpoints relacionados a Cidades.*/
@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation(value = "Busca lista de todas as cidades")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de cidades encontrada")})
    ResponseEntity<List<CidadeDTO>> listar();


    @ApiOperation(value = "Busca cidade pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade encontrada"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class),})
    ResponseEntity<CidadeDTO> buscaPorId(@ApiParam(name = "id", value = "ID da cidade", example = "1") Long id);


    @ApiOperation("Busca lista de cidades pelo nome")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Lista de cidades encontrada"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class)})
    ResponseEntity<List<CidadeDTO>> buscaPorId(@ApiParam(name = "nome", value = "nome da cidade", example = "São Paulo") String nome);


    @ApiOperation("Cadastra uma nova cidade")
    @ApiResponses({@ApiResponse(code = 201, message = "Cidade cadastrada")})
    @ResponseStatus(HttpStatus.CREATED) // para visualização na documentação apenas o status code 201 de sucesso
    ResponseEntity<CidadeDTO> salvar(@ApiParam(name = "payload", value = "Representação de uma nova cidade") CidadeInput cidadeInput);


    @ApiOperation("Atualiza cidade pelo ID")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade atualizada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class)})
    ResponseEntity<CidadeDTO> alterar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") Long id,
        @ApiParam(name = "payload", value = "Representação de uma nova cidade com os novos dados") CidadeInput cidadeInput);


    @ApiOperation(value = "Exclui cidade pelo ID")
    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade deletada"),
        @ApiResponse(code = 400, message = "Requisição inválida (erro do cliente)", response = StandardErrorBadRequest.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardErrorNotFound.class)})
    @ResponseStatus(value = HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    ResponseEntity<Void> deletar(@ApiParam(name = "id", value = "ID da cidade", example = "1") Long id);
}
