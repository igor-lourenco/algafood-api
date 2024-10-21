package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.domain.services.CozinhaService;
import com.algaworks.algafood.swaggerOpenApi.controllers.CozinhaControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaService cozinhaService;

    @GetMapping
    public ResponseEntity<List<CozinhaDTO>> listar() {
        List<CozinhaDTO> cozinhaDTOS = cozinhaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTOS);

    }


    @GetMapping("/page")
    public ResponseEntity<Page<CozinhaDTO>> listaPageable(@PageableDefault(size = 12) Pageable pageable) {
        Page<CozinhaDTO> cozinhaDTOS = cozinhaService.listar(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTOS);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<CozinhaDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        CozinhaDTO cozinhaDTO = cozinhaService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);

    }

    @PostMapping
    public ResponseEntity<CozinhaDTO> salvar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        CozinhaDTO cozinhaDTO = cozinhaService.salvar(cozinhaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CozinhaDTO> alterar(@PathVariable(value = "id") Long id, @Valid @RequestBody CozinhaInput cozinhaInput) {
        CozinhaDTO cozinhaDTO = cozinhaService.alterar(id, cozinhaInput);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deletar(@PathVariable(value = "id") Long id) {
        cozinhaService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<CozinhaDTO>> buscaPorId(@RequestParam(value = "nome") String nome) {
        List<CozinhaDTO> listaCozinhaPorNome = cozinhaService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);

    }
}
