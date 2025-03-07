package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.services.CidadeService;
import com.algaworks.algafood.swaggerOpenApi.controllers.CidadeControllerOpenApi;
import com.algaworks.algafood.utils.ControllerUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping(path = "/v1/cidades")
@Tag(name = "Cidades")
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;


    @CheckSecurity.Cozinhas.PodeConsultar
    @Deprecated // Deprecia a API e o SpringDoc também mostra a API deprecidada na documentação
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTO>> lista() {
        log.info("REQUEST - GET [lista]");

        CollectionModel<CidadeDTO> cidadeDTOS = cidadeService.listar();

        log.info("RESPONSE - GET [lista] :: STATUS CODE : {}", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTOS);
    }


    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        log.info("REQUEST - GET [buscaPorId] :: id: {}", id);

        CidadeDTO cidadeDTO = cidadeService.buscaPorId(id);

        log.info("RESPONSE - GET [buscaPorId] :: BODY: {}", new JSONObject(cidadeDTO).toString());
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @CheckSecurity.Cozinhas.PodeConsultar
    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        log.info("REQUEST - GET [buscaPorNome] :: nome: {}", nome);

        CollectionModel<CidadeDTO> listaCidadePorNome = cidadeService.consultaPorNome(nome);

        log.info("RESPONSE - GET [buscaPorNome] :: STATUS CODE: {}", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);
    }


    @CheckSecurity.Cozinhas.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> salva(@Valid @RequestBody CidadeInput cidadeInput) {
        log.info("REQUEST - POST [salva] :: PAYLOAD: {}", new JSONObject(cidadeInput));

        CidadeDTO cidadeDTO = cidadeService.salvar(cidadeInput);

//      Adiciona no cabeçalho de retorno o campo 'Location' com a URI do novo recurso criado
        ControllerUtils.adicionaUriNoHeaderDaResposta(cidadeDTO.getId());

        log.info("RESPONSE - POST [salva] :: BODY: {}", new JSONObject(cidadeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }


    @CheckSecurity.Cozinhas.PodeEditar
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> altera(@PathVariable(value = "id") Long id,
        @Valid @RequestBody CidadeInput cidadeInput) {
        log.info("REQUEST - PUT [altera] :: id: {}, PAYLOAD: {}", id, new JSONObject(cidadeInput));

        CidadeDTO cidadeDTO = cidadeService.alterar(id, cidadeInput);

        log.info("RESPONSE - PUT [altera] :: BODY: {}", new JSONObject(cidadeDTO));
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @CheckSecurity.Cozinhas.PodeEditar
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta( @PathVariable(value = "id") Long id) {
        log.info("REQUEST - DELETE [deleta] :: id: {}", id);

        cidadeService.deletar(id);

        log.info("RESPONSE - DELETE [deleta] :: STATUS CODE: {}", HttpStatus.NO_CONTENT);
    }
}
