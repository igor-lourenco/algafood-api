package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.domain.services.CidadeService;
import com.algaworks.algafood.swaggerOpenApi.controllers.CidadeControllerOpenApi;
import com.algaworks.algafood.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/cidades")
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTO>> lista() {
        CollectionModel<CidadeDTO> cidadeDTOS = cidadeService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTOS);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        CidadeDTO cidadeDTO = cidadeService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        CollectionModel<CidadeDTO> listaCidadePorNome = cidadeService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> salva(@Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.salvar(cidadeInput);

//      Adiciona no cabeçalho de retorno o campo 'Location' com a URI do novo recurso criado
        ControllerUtils.adicionaUriNoHeaderDaResposta(cidadeDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> altera(@PathVariable(value = "id") Long id,
        @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.alterar(id, cidadeInput);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @DeleteMapping(value = "/{id}")
    public void deleta( @PathVariable(value = "id") Long id) {
        cidadeService.deletar(id);
    }
}
