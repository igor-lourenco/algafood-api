package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.inputs.FormaPagamentoInput;
import com.algaworks.algafood.domain.services.FormaPagamentoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.FormaPagamentoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/formas-pagamento", produces = {MediaType.APPLICATION_JSON_VALUE})
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoDTO>> lista(ServletWebRequest request) {

//      Desativa o cache de conteúdo para esta requisição específica, garantindo que o filtro ShallowEtagHeaderFilter
//      não armazene o corpo da resposta em cache. Isso permite controlar manualmente o comportamento de cache.
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

//      Pega a última data de atualização na tabela
        String eTag = formaPagamentoService.findDataUltimaAtualizacao();

//      Se os valores coincidirem, significa que o recurso não foi modificado desde a última requisição do cliente.
        if (request.checkNotModified(eTag)) { // Quando o método checkNotModified retorna true, ele já configurou a resposta com o status HTTP 304.

            System.out.println("Header: If-None-Match é igual a ETag");
            System.out.println("If-None-Match: " + request.getHeader("If-None-Match"));
            System.out.println("ETag: " + eTag);

//          Ao retornar null no controller após a chamada de checkNotModified, o Spring entende que a resposta foi corretamente manipulada e
//          retorna STATUS CODE 304 Not Modified, informando ao cliente que pode usar a versão em cache do recurso.
            return null;
        }

        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.lista();

        return ResponseEntity
            .status(HttpStatus.OK)
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) //  cliente armazene a resposta em cache por até 10 segundos e que o cache seja público.
            .eTag(eTag)  // Inclui o ETag gerado no cabeçalho da resposta.
            .body(formaPagamentoDTOS);
    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> buscaPorId(@PathVariable(value = "id") Long id, ServletWebRequest request) {

//      Desativa o cache de conteúdo para esta requisição específica, garantindo que o filtro ShallowEtagHeaderFilter
//      não armazene o corpo da resposta em cache. Isso permite controlar manualmente o comportamento de cache.
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

//      Pega a última data de atualização na tabela
        String eTag = formaPagamentoService.findDataUltimaAtualizacaoById(id);

//      Se os valores coincidirem, significa que o recurso não foi modificado desde a última requisição do cliente.
        if (request.checkNotModified(eTag)) { // Quando o método checkNotModified retorna true, ele já configurou a resposta com o status HTTP 304.

            System.out.println("Header: If-None-Match é igual a ETag");
            System.out.println("If-None-Match: " + request.getHeader("If-None-Match"));
            System.out.println("ETag: " + eTag);

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


    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<FormaPagamentoDTO>> buscaPorNome(@RequestParam(value = "descricao") String descricao) {
        List<FormaPagamentoDTO> formaPagamentoDTOS = formaPagamentoService.consultaPorNome(descricao);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);

    }


    @PostMapping
    public ResponseEntity<FormaPagamentoDTO> salva(@Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.salva(formaPagamentoInput);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<FormaPagamentoDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamentoDTO formaPagamentoDTO = formaPagamentoService.altera(id, formaPagamentoInput);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTO);

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleta(@PathVariable(value = "id") Long id) {
        formaPagamentoService.deleta(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
