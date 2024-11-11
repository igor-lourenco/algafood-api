package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.inputs.EstadoInput;
import com.algaworks.algafood.domain.services.EstadoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.EstadoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController implements EstadoControllerOpenApi {

    @Autowired
    private EstadoService estadoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoDTO>> lista() {
        List<EstadoDTO> estadoDTOs = estadoService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(estadoDTOs);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        EstadoDTO estadoDTO = estadoService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(estadoDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EstadoDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        List<EstadoDTO> listaEstadoPorNome = estadoService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaEstadoPorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> salva(@RequestBody @Valid EstadoInput estadoInput) {
        EstadoDTO estadoDTO = estadoService.salvar(estadoInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EstadoDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody EstadoInput estadoInput) {
        EstadoDTO estadoDTO = estadoService.alterar(id, estadoInput);
        return ResponseEntity.status(HttpStatus.OK).body(estadoDTO);
    }


    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id) {
        estadoService.deletar(id);
    }

}
