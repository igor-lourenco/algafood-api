package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.EstadoModel;
import com.algaworks.algafood.domain.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


//    @PostMapping
//    public ResponseEntity<CidadeModel> salvar(@RequestBody @Valid CidadeModel cidadeModel) {
//        cidadeModel = cidadeService.salvar(cidadeModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeModel);
//
//    }


//    @PutMapping(value = "/{id}")
//    public ResponseEntity<?> alterar(@PathVariable(value = "id") Long id, @RequestBody CidadeModel cidadeModel) {
//        CidadeModel obj = cidadeService.alterar(id, cidadeModel);
//        return ResponseEntity.status(HttpStatus.OK).body(obj);
//
//    }


//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
//        cidadeService.deletar(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//
//    }

//    @GetMapping(value = "/consulta-por-nome")
//    public ResponseEntity<?> buscaPorId(@RequestParam(value = "nome") String nome) {
//        List<CozinhaModel> listaCozinhaPorNome = cozinhaService.consultaPorNome(nome);
//        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);
//
//    }

}
