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
@RequestMapping(value = "/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaService cozinhaService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CozinhaDTO>> lista() {
        List<CozinhaDTO> cozinhaDTOS = cozinhaService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTOS);
    }


    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CozinhaDTO> listaPageable(@PageableDefault(size = 12) Pageable pageable) {
        Page<CozinhaDTO> cozinhaDTOS = cozinhaService.listar(pageable);
        return cozinhaDTOS;
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        CozinhaDTO cozinhaDTO = cozinhaService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CozinhaDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        List<CozinhaDTO> listaCozinhaPorNome = cozinhaService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTO> salva(@RequestBody @Valid CozinhaInput cozinhaInput) {
        CozinhaDTO cozinhaDTO = cozinhaService.salvar(cozinhaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody CozinhaInput cozinhaInput) {
        CozinhaDTO cozinhaDTO = cozinhaService.alterar(id, cozinhaInput);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);
    }


    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id) {
        cozinhaService.deletar(id);
    }

}
