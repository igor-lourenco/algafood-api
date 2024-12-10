package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.swaggerOpenApi.controllers.GrupoControllerOpenApi;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.domain.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/grupos")
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<GrupoDTO>> lista() {
        CollectionModel<GrupoDTO> grupoDTOS = grupoService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GrupoDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        GrupoDTO grupoDTO = grupoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<GrupoDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        CollectionModel<GrupoDTO> formaPagamentoDTOS = grupoService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GrupoDTO> salva(@Valid @RequestBody GrupoInput grupoInput) {
        GrupoDTO grupoDTO = grupoService.salva(grupoInput);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GrupoDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody GrupoInput grupoInput) {
        GrupoDTO grupoDTO = grupoService.altera(id, grupoInput);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTO);
    }


    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id) {
        grupoService.deleta(id);
    }
}
