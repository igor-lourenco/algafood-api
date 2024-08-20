package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.core.constraints.groups.Groups;
import com.algaworks.algafood.domain.models.CidadeModel;
import com.algaworks.algafood.domain.models.CozinhaModel;
import com.algaworks.algafood.domain.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cidades", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<CidadeModel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(cidadeService.listar());

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable(value = "id") Long id) {
        CidadeModel cozinha = cidadeService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinha);

    }


    @PostMapping
    public ResponseEntity<CidadeModel> salvar(
        @Validated(Groups.CadastroCidade.class)
        @RequestBody CidadeModel cidadeModel) {

        cidadeModel = cidadeService.salvar(cidadeModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeModel);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterar(
        @PathVariable(value = "id") Long id,
        @RequestBody CidadeModel cidadeModel) {

        CidadeModel obj = cidadeService.alterar(id, cidadeModel);
        return ResponseEntity.status(HttpStatus.OK).body(obj);
    }


//    @DeleteMapping(value = "/{id}")
//    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
//        cidadeService.deletar(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//
//    }

    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<CidadeModel>> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<CidadeModel> listaCidadePorNome = cidadeService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);

    }

}
