package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.services.RestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteModel>> listar(){
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.listar());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteModel> buscaPorId(@PathVariable(value = "id") Long id){
        RestauranteModel restauranteModel = restauranteService.buscaPorId(id);

        if(restauranteModel == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restauranteModel);
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody RestauranteModel restauranteModel) {
        try {
            restauranteModel = restauranteService.salvar(restauranteModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteModel);
        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterar(@PathVariable(value = "id") Long id, @RequestBody RestauranteModel restauranteModel){
        try {
            RestauranteModel obj = restauranteService.alterar(id, restauranteModel);
            return ResponseEntity.status(HttpStatus.OK).body(obj);

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RestauranteModel> deletar(@PathVariable(value = "id") Long id) {
        try {
            restauranteService.deletar(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (EntidadeNaoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (EntidadeEmUsoException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

    }
}
