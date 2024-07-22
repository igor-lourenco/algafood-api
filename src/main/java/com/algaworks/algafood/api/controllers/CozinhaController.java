package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.models.Cozinha;
import com.algaworks.algafood.infrastructure.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CozinhaController {


    @Autowired
    private CozinhaRepository repository;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar(){

        List<Cozinha> listaCozinhas  = repository.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cozinha> buscaPorId(@PathVariable(value = "id") Long id){

        Cozinha cozinha = repository.findById(id).orElse(null);

        if(cozinha == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cozinha);
    }

    @PostMapping
    public ResponseEntity<Cozinha> salvar(@RequestBody Cozinha cozinha){

        cozinha = repository.save(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinha);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Cozinha> alterar(@PathVariable(value = "id") Long id, @RequestBody Cozinha cozinha){

        Cozinha obj = repository.findById(id).orElse(null);

        if(obj == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        obj.setNome(cozinha.getNome());
        obj = repository.save(obj);

        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Cozinha> deletar(@PathVariable(value = "id") Long id){

        Cozinha cozinha = repository.findById(id).orElse(null);

        if(cozinha == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        repository.delete(cozinha);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
