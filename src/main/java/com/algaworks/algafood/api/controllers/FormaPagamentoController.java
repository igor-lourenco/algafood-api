package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.services.FormaPagamentoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.FormaPagamentoControllerOpenApi;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import jakarta.validation.Valid;
import java.util.concurrent.TimeUnit;

@Log4j2
@RestController
@RequestMapping(path = "/v1/formas-pagamento")
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> lista(ServletWebRequest request) {

//      Desativa o cache de conteúdo para esta requisição específica, garantindo que o filtro ShallowEtagHeaderFilter
//      não armazene o corpo da resposta em cache. Isso permite controlar manualmente o comportamento de cache.
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

//      Pega a última data de atualização na tabela
        String eTag = formaPagamentoService.buscaDataUltimaAtualizacao();

//      Se os valores coincidirem, significa que o recurso não foi modificado desde a última requisição do cliente.
        if (request.checkNotModified(eTag)) { // Quando o método checkNotModified retorna true, ele já configurou a resposta com o status HTTP 304.

            log.info("Header: If-None-Match é igual a ETag");
            log.info("If-None-Match: {}", request.getHeader("If-None-Match"));
            log.info("ETag: {}", eTag);

//          Ao retornar null no controller após a chamada de checkNotModified, o Spring entende que a resposta foi corretamente manipulada e
//          retorna STATUS CODE 304 Not Modified, informando ao cliente que pode usar a versão em cache do recurso.
            return null;
        }

        CollectionModel<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.lista();

        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) //  cliente armazene a resposta em cache por até 10 segundos e que o cache seja público.
            .eTag(eTag)  // Inclui o ETag gerado no cabeçalho da resposta.
            .body(formaPagamentoDTOS);
    }

    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoDTO> buscaPorId(@PathVariable(value = "id") Long id, ServletWebRequest request) {

//      Desativa o cache de conteúdo para esta requisição específica, garantindo que o filtro ShallowEtagHeaderFilter
//      não armazene o corpo da resposta em cache. Isso permite controlar manualmente o comportamento de cache.
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

//      Pega a última data de atualização na tabela
        String eTag = formaPagamentoService.buscaDataUltimaAtualizacaoPorId(id);

//      Se os valores coincidirem, significa que o recurso não foi modificado desde a última requisição do cliente.
        if (request.checkNotModified(eTag)) { // Quando o método checkNotModified retorna true, ele já configurou a resposta com o status HTTP 304.

            log.info("Header: If-None-Match é igual a ETag");
            log.info("If-None-Match: {}", request.getHeader("If-None-Match"));
            log.info("ETag: {}", eTag);


//          Ao retornar null no controller após a chamada de checkNotModified, o Spring entende que a resposta foi corretamente manipulada e
//          retorna STATUS CODE 304 Not Modified, informando ao cliente que pode usar a versão em cache do recurso.
            return null;
        }

        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.findById(id);

        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) //  cliente armazene a resposta em cache por até 10 segundos e que o cache seja público.
            .eTag(eTag)  // Inclui o ETag gerado no cabeçalho da resposta.
            .body(formaPagamentoDTO);
    }


    @CheckSecurity.FormasPagamento.PodeConsultar
    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> buscaPorNome(@RequestParam(value = "descricao") String descricao) {
        CollectionModel<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.consultaPorNome(descricao);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);
    }


    @CheckSecurity.FormasPagamento.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoDTO> salva(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.salva(formaPagamentoInput);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);
    }


    @CheckSecurity.FormasPagamento.PodeEditar
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FormaPagamentoDTO> altera(
        @PathVariable(value = "id") Long id,
        @Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {

        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.altera(id, formaPagamentoInput);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);
    }


    @CheckSecurity.FormasPagamento.PodeEditar
    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id) {
        formaPagamentoService.deleta(id);
    }
}
