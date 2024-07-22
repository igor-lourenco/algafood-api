package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.models.Cozinha;
import com.algaworks.algafood.infrastructure.repositories.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<Cozinha> listar(){

        return repository.findAll();
    }

    @GetMapping(value = "/{id}")
    public Cozinha buscaPorId(@PathVariable(value = "id") Long id){

        return repository.findById(id).get();


    }


}
