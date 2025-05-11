package com.algaworks.algafood.api.controllersV2;


import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.api.inputs.CidadeInputV2;
import com.algaworks.algafood.domain.services.CidadeServiceV2;
import com.algaworks.algafood.swaggerOpenApi.controllers.CidadeControllerOpenApiV2;
import com.algaworks.algafood.utils.ControllerUtils;
import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@Log4j2
@RestController
@RequestMapping(path = "/v2/cidades")
public class CidadeControllerV2  implements CidadeControllerOpenApiV2 {

    @Autowired
    private CidadeServiceV2 cidadeServiceV2;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTOV2>> lista() {
        log.info("REQUEST - GET [lista]");

        CollectionModel<CidadeDTOV2> cidadeDTOS = cidadeServiceV2.listar();

        log.info("RESPONSE - GET [lista] :: STATUS CODE : {}", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTOS);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTOV2> buscaPorId(@PathVariable(value = "id") Long id) {
        log.info("REQUEST - GET [buscaPorId] :: id: {}", id);

        CidadeDTOV2 cidadeDTO = cidadeServiceV2.buscaPorId(id);

        log.info("RESPONSE - GET [buscaPorId] :: BODY: {}", new JSONObject(cidadeDTO).toString());
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTOV2>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        log.info("REQUEST - GET [buscaPorNome] :: nome: {}", nome);

        CollectionModel<CidadeDTOV2> listaCidadePorNome = cidadeServiceV2.consultaPorNome(nome);

        log.info("RESPONSE - GET [buscaPorNome] :: STATUS CODE: {}", HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTOV2> salva(@Valid @RequestBody CidadeInputV2 cidadeInput) {
        log.info("REQUEST - POST [salva] :: PAYLOAD: {}", new JSONObject(cidadeInput));

        CidadeDTOV2 cidadeDTO = cidadeServiceV2.salvar(cidadeInput);

//      Adiciona no cabeçalho de retorno o campo 'Location' com a URI do novo recurso criado
        ControllerUtils.adicionaUriNoHeaderDaResposta(cidadeDTO.getId());

        log.info("RESPONSE - POST [salva] :: BODY: {}", new JSONObject(cidadeDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTOV2> altera(@PathVariable(value = "id") Long id,
        @Valid @RequestBody CidadeInputV2 cidadeInput) {
        log.info("REQUEST - PUT [altera] :: id: {}, PAYLOAD: {}", id, new JSONObject(cidadeInput));

        CidadeDTOV2 cidadeDTO = cidadeServiceV2.alterar(id, cidadeInput);

        log.info("RESPONSE - PUT [altera] :: BODY: {}", new JSONObject(cidadeDTO));
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleta( @PathVariable(value = "id") Long id) {
        log.info("REQUEST - DELETE [deleta] :: id: {}", id);

        cidadeServiceV2.deletar(id);

        log.info("RESPONSE - DELETE [deleta] :: STATUS CODE: {}", HttpStatus.NO_CONTENT);
    }
}
