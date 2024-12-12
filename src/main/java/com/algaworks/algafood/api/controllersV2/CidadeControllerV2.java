package com.algaworks.algafood.api.controllersV2;


import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.api.inputs.CidadeInputV2;
import com.algaworks.algafood.domain.services.CidadeServiceV2;
import com.algaworks.algafood.swaggerOpenApi.controllers.CidadeControllerOpenApiV2;
import com.algaworks.algafood.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v2/cidades")
public class CidadeControllerV2  implements CidadeControllerOpenApiV2 {

    @Autowired
    private CidadeServiceV2 cidadeServiceV2;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTOV2>> lista() {
        CollectionModel<CidadeDTOV2> cidadeDTOS = cidadeServiceV2.listar();
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTOS);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTOV2> buscaPorId(@PathVariable(value = "id") Long id) {
        CidadeDTOV2 cidadeDTO = cidadeServiceV2.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTOV2>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        CollectionModel<CidadeDTOV2> listaCidadePorNome = cidadeServiceV2.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTOV2> salva(@Valid @RequestBody CidadeInputV2 cidadeInput) {

        CidadeDTOV2 cidadeDTO = cidadeServiceV2.salvar(cidadeInput);

//      Adiciona no cabeçalho de retorno o campo 'Location' com a URI do novo recurso criado
        ControllerUtils.adicionaUriNoHeaderDaResposta(cidadeDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTOV2> altera(@PathVariable(value = "id") Long id,
        @Valid @RequestBody CidadeInputV2 cidadeInput) {

        CidadeDTOV2 cidadeDTO = cidadeServiceV2.alterar(id, cidadeInput);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta( @PathVariable(value = "id") Long id) {
        cidadeServiceV2.deletar(id);
    }
}
