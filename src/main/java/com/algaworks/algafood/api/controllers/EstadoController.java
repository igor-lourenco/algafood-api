package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.inputs.EstadoInput;
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
    public ResponseEntity<List<EstadoDTO>> listar() {
        List<EstadoDTO> estadoDTOs = estadoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(estadoDTOs);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        EstadoDTO estadoDTO = estadoService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(estadoDTO);

    }

    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<EstadoDTO>> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<EstadoDTO> listaEstadoPorNome = estadoService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaEstadoPorNome);

    }

    @PostMapping
    public ResponseEntity<EstadoDTO> salvar(@RequestBody @Valid EstadoInput estadoInput) {
        EstadoDTO estadoDTO = estadoService.salvar(estadoInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoDTO);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<EstadoDTO> alterar(@PathVariable(value = "id") Long id, @Valid @RequestBody EstadoInput estadoInput) {
        EstadoDTO estadoDTO = estadoService.alterar(id, estadoInput);
        return ResponseEntity.status(HttpStatus.OK).body(estadoDTO);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EstadoModel> deletar(@PathVariable(value = "id") Long id) {
        estadoService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
