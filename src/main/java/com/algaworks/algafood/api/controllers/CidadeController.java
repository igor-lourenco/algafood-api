package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.domain.services.CidadeService;
import io.swagger.annotations.Api;
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

    @GetMapping
    public ResponseEntity<List<CidadeDTO>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        CidadeDTO cidadeDTO = cidadeService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);

    }


    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<CidadeDTO>> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<CidadeDTO> listaCidadePorNome = cidadeService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);

    }


    @PostMapping
    public ResponseEntity<CidadeDTO> salvar(@Valid @RequestBody CidadeInput cidadeInput) {
        CidadeDTO cidadeDTO = cidadeService.salvar(cidadeInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<CidadeDTO> alterar(@PathVariable(value = "id") Long id, @Valid @RequestBody CidadeInput cidadeInput) {
        CidadeDTO cidadeDTO = cidadeService.alterar(id, cidadeInput);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
        cidadeService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
