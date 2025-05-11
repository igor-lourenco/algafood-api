package com.algaworks.algafood.api.controllersV2;


import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.api.inputs.CozinhaInputV2;
import com.algaworks.algafood.domain.services.CozinhaServiceV2;
import com.algaworks.algafood.swaggerOpenApi.controllers.CozinhaControllerOpenApiV2;
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

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cozinhas")
public class CozinhaControllerV2  implements CozinhaControllerOpenApiV2 {

    @Autowired
    private CozinhaServiceV2 cozinhaServiceV2;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CozinhaDTOV2>> lista() {
        CollectionModel<CozinhaDTOV2> cozinhaDTOS = cozinhaServiceV2.lista();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTOS);
    }


    @GetMapping(value = "/page", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<CozinhaDTOV2> listaPageable(@PageableDefault(size = 12) Pageable pageable) {
        Page<CozinhaDTOV2> cozinhaDTOS = cozinhaServiceV2.listaPaginada(pageable);
        return cozinhaDTOS;
    }


    @GetMapping(value = "/page-com-links", produces = MediaType.APPLICATION_JSON_VALUE)
    public PagedModel<CozinhaDTOV2> listaPageableComLinks(@PageableDefault(size = 12) Pageable pageable) {
        PagedModel<CozinhaDTOV2> cozinhaDTOS = cozinhaServiceV2.listaPaginadaComLinks(pageable);
        return cozinhaDTOS;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTOV2> buscaPorId(@PathVariable(value = "id") Long id) {
        CozinhaDTOV2 cozinhaDTO = cozinhaServiceV2.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CozinhaDTOV2>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        CollectionModel<CozinhaDTOV2> listaCozinhaPorNome = cozinhaServiceV2.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCozinhaPorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTOV2> salva(@RequestBody @Valid CozinhaInputV2 cozinhaInput) {
        CozinhaDTOV2 cozinhaDTO = cozinhaServiceV2.salvar(cozinhaInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CozinhaDTOV2> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody CozinhaInputV2 cozinhaInput) {
        CozinhaDTOV2 cozinhaDTO = cozinhaServiceV2.alterar(id, cozinhaInput);
        return ResponseEntity.status(HttpStatus.OK).body(cozinhaDTO);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta(@PathVariable(value = "id") Long id) {
        cozinhaServiceV2.deletar(id);
    }

}
