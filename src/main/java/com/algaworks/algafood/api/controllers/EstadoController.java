package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estados", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public ResponseEntity<List<EstadoModel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(estadoService.listar());

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<EstadoModel> buscaPorId(@PathVariable(value = "id") Long id) {
        EstadoModel cozinha = estadoService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinha);

    }

    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<EstadoModel>> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<EstadoModel> listaEstadoPorNome = estadoService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaEstadoPorNome);

    }

//    @PostMapping
//    public ResponseEntity<EstadoModel> salvar(@RequestBody @Valid EstadoModel estadoModel) {
//        estadoModel = estadoService.salvar(estadoModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(estadoModel);
//
//    }


//    @PutMapping(value = "/{id}")
//    public ResponseEntity<EstadoModel> alterar(@PathVariable(value = "id") Long id, @RequestBody EstadoModel estadoModel) {
//        EstadoModel obj = estadoService.alterar(id, estadoModel);
//        return ResponseEntity.status(HttpStatus.OK).body(obj);
//
//    }


//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<EstadoModel> deletar(@PathVariable(value = "id") Long id) {
//        estadoService.deletar(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//
//    }

}
