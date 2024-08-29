package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.domain.services.EstadoService;
import com.algaworks.algafood.domain.services.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/formas-pagamento", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> listar() {
        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> findById(@PathVariable(value = "id") Long id){
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);
    }


    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<FormaPagamentoDTO>> findByNome(@RequestParam(value = "descricao") String descricao) {
        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.consultaPorNome(descricao);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);

    }


    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> salva(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput){
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.salva(formaPagamentoInput);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody FormaPagamentoInput formaPagamentoInput){
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.altera(id, formaPagamentoInput);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleta(@PathVariable(value = "id") Long id){
        formaPagamentoService.deleta(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
