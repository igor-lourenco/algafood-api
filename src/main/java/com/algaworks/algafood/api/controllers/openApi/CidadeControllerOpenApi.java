package com.algaworks.algafood.api.controllers.openApi;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.inputs.CidadeInput;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Api(tags = "Cidades")
public interface CidadeControllerOpenApi {

    @ApiOperation(value = "Busca lista de todas as cidades")
    @ApiModelProperty(position = 0)
    ResponseEntity<List<CidadeDTO>> listar();

    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da cidade inválido", response = StandardError.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardError.class)
    })
    @ApiOperation(value = "Busca cidade pelo ID")
    ResponseEntity<CidadeDTO> buscaPorId(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") Long id);


    @ApiOperation("Busca lista de cidades pelo nome")
    ResponseEntity<List<CidadeDTO>> buscaPorId(
        @ApiParam(name = "nome", value = "nome da cidade", example = "São Paulo")  String nome);

    @ApiResponses({
        @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    @ApiOperation("Cadastra uma nova cidade")
    ResponseEntity<CidadeDTO> salvar(
        @ApiParam(name = "payload", value = "Representação de uma nova cidade") CidadeInput cidadeInput);

    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade atualizada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardError.class)
    })
    @ApiOperation("Atualiza cidade pelo ID")
    ResponseEntity<CidadeDTO> alterar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") Long id,
        @ApiParam(name = "payload", value = "Representação de uma nova cidade com os novos dados") CidadeInput cidadeInput);


    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade deletada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardError.class)
    })
    @ApiOperation(value = "Exclui cidade pelo ID")
    void deletar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") Long id);
}
