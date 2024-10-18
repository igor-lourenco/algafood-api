package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.controllers.exceptions.StandardError;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.domain.services.CidadeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades") // Mapeia essa tag "Cidades" com a tag declarada na classe SpringFoxConfig da aplicação, para ser visualizada na documentação.
@RestController
@RequestMapping(value = "/cidades", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;


    @ApiOperation(value = "Busca lista de todas as cidades")
    @ApiModelProperty(position = 0)
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());
    }


    @ApiResponses({
        @ApiResponse(code = 400, message = "ID da cidade inválido", response = StandardError.class),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardError.class)
    })
    @ApiOperation(value = "Busca cidade pelo ID")
    @GetMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> buscaPorId(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") @PathVariable(value = "id") Long id) {

        CidadeDTO cidadeDTO = cidadeService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @ApiOperation("Busca lista de cidades pelo nome")
    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<CidadeDTO>> buscaPorId(
        @ApiParam(name = "nome", value = "nome da cidade", example = "São Paulo") @RequestParam(value = "nome") String nome) {

        List<CidadeDTO> listaCidadePorNome = cidadeService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);
    }

    @ApiResponses({
        @ApiResponse(code = 201, message = "Cidade cadastrada")
    })
    @ApiOperation("Cadastra uma nova cidade")
    @PostMapping
    public ResponseEntity<CidadeDTO> salvar(
        @ApiParam(name = "payload", value = "Representação de uma nova cidade") @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.salvar(cidadeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }

    @ApiResponses({
        @ApiResponse(code = 200, message = "Cidade atualizada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardError.class)
    })
    @ApiOperation("Atualiza cidade pelo ID")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> alterar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") @PathVariable(value = "id") Long id,
        @ApiParam(name = "payload", value = "Representação de uma nova cidade com os novos dados") @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.alterar(id, cidadeInput);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @ApiResponses({
        @ApiResponse(code = 204, message = "Cidade deletada"),
        @ApiResponse(code = 404, message = "Cidade não encontrada", response = StandardError.class)
    })
    @ApiOperation(value = "Exclui cidade pelo ID")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    public void deletar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") @PathVariable(value = "id") Long id) {

        cidadeService.deletar(id);
    }

}
