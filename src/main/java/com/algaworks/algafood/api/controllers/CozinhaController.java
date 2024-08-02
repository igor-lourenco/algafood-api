package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.services.CozinhaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CozinhaController {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<CozinhaModel>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable(value = "id") Long id) {
//        try {
            CozinhaModel cozinha = cozinhaService.buscaPorId(id);
            return ResponseEntity.status(HttpStatus.OK).body(cozinha);

//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }
    }

    @PostMapping
    public ResponseEntity<CozinhaModel> salvar(@RequestBody CozinhaModel cozinha){
        cozinha = cozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterar(@PathVariable(value = "id") Long id, @RequestBody CozinhaModel cozinha){
//        try{
        CozinhaModel obj = cozinhaService.alterar(id, cozinha);
        return ResponseEntity.status(HttpStatus.OK).body(obj);

//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
//        try {
            cozinhaService.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

//        } catch (EntidadeNaoEncontradaException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//        } catch (EntidadeEmUsoException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }

    }

    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<?> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<CozinhaModel> listaCozinhaPorNome = cozinhaService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);
    }

}
