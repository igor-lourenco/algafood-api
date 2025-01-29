package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.inputs.CozinhaInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.services.CozinhaService;
import com.algaworks.algafood.swaggerOpenApi.controllers.CozinhaControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/cozinhas")
public class CozinhaController implements CozinhaControllerOpenApi {

    @Autowired
    private CozinhaService cozinhaService;


    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CozinhaDTO>> lista() {
        CollectionModel<CozinhaDTO> cozinhaDTOS = cozinhaService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTOS);
    }


    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CozinhaDTO> listaPageable(@PageableDefault(size = 12) Pageable pageable) {
        Page<CozinhaDTO> cozinhaDTOS = cozinhaService.listaPaginada(pageable);
        return cozinhaDTOS;
    }


    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/page-com-links", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<CozinhaDTO> listaPageableComLinks(@PageableDefault(size = 12) Pageable pageable) {
        PagedModel<CozinhaDTO> cozinhaDTOS = cozinhaService.listaPaginadaComLinks(pageable);
        return cozinhaDTOS;
    }

    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        CozinhaDTO cozinhaDTO = cozinhaService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);
    }


    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CozinhaDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        CollectionModel<CozinhaDTO> listaCozinhaPorNome = cozinhaService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);
    }


    @CheckSecurity.Cozinhas.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTO> salva(@RequestBody @Valid CozinhaInput cozinhaInput) {
        CozinhaDTO cozinhaDTO = cozinhaService.salvar(cozinhaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);
    }


    @CheckSecurity.Cozinhas.PodeEditar
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody CozinhaInput cozinhaInput) {
        CozinhaDTO cozinhaDTO = cozinhaService.alterar(id, cozinhaInput);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);
    }


    @CheckSecurity.Cozinhas.PodeEditar
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable(value = "id") Long id) {
        cozinhaService.deletar(id);
    }

}
