package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.services.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<CozinhaDTO>> listar() {
        List<CozinhaDTO> cozinhaDTOS = cozinhaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTOS);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CozinhaDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        CozinhaDTO cozinhaDTO = cozinhaService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);

    }

    @PostMapping
    public ResponseEntity<CozinhaDTO> salvar(@RequestBody @Valid CozinhaModel cozinha) {
        CozinhaDTO cozinhaDTO = cozinhaService.salvar(cozinha);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CozinhaDTO> alterar(@PathVariable(value = "id") Long id, @RequestBody CozinhaModel cozinha) {
        CozinhaDTO cozinhaDTO = cozinhaService.alterar(id, cozinha);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
        cozinhaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<?> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<CozinhaModel> listaCozinhaPorNome = cozinhaService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);

    }
}
