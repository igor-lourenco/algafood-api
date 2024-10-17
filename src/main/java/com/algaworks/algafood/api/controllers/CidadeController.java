package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.domain.services.CidadeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Cidades")
// Mapeia essa tag "Cidades" com a tag declarada na classe SpringFoxConfig da aplicação, para ser visualizada na documentação.
@RestController
@RequestMapping(value = "/cidades", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @ApiOperation("Busca lista de todas as cidades")
    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());
    }

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

    @ApiOperation("Cadastra uma nova cidade")
    @PostMapping
    public ResponseEntity<CidadeDTO> salvar(
        @ApiParam(name = "payload", value = "Representação de uma nova cidade") @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.salvar(cidadeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }

    @ApiOperation("Atualiza cidade pelo ID")
    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> alterar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") @PathVariable(value = "id") Long id,
        @ApiParam(name = "payload", value = "Representação de uma nova cidade com os novos dados") @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.alterar(id, cidadeInput);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }

    @ApiOperation(value = "Exclui cidade pelo ID")
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // para visualização na documentação apenas o status code 204 de sucesso
    public ResponseEntity<?> deletar(
        @ApiParam(name = "id", value = "ID da cidade", example = "1") @PathVariable(value = "id") Long id) {

        cidadeService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
