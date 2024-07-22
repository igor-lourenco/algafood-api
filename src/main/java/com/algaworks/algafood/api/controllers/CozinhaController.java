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

        Cozinha cozinha = repository.findById(id).get();

        return ResponseEntity.status(HttpStatus.OK).body(cozinha);


    }
}
