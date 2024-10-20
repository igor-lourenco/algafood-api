package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.controllers.openApi.GrupoControllerOpenApi;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.domain.services.GrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class GrupoController implements GrupoControllerOpenApi {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> lista() {

        List<GrupoDTO> grupoDTOS = grupoService.lista();

        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<GrupoDTO> buscaPorId(@PathVariable(value = "id") Long id) {

        GrupoDTO grupoDTO = grupoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTO);
    }


    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<GrupoDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {

        List<GrupoDTO> formaPagamentoDTOS = grupoService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);
    }


    @PostMapping
    public ResponseEntity<GrupoDTO> salva(@Valid @RequestBody GrupoInput grupoInput) {

        GrupoDTO grupoDTO = grupoService.salva(grupoInput);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTO);
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<GrupoDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody GrupoInput grupoInput) {

        GrupoDTO grupoDTO = grupoService.altera(id, grupoInput);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTO);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleta(@PathVariable(value = "id") Long id) {

        grupoService.deleta(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
