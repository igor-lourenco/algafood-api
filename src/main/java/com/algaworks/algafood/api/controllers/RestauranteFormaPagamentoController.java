package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.services.RestauranteFormaPagamentoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteFormaPagamentoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private RestauranteFormaPagamentoService service;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<FormaPagamentoDTO>> buscaFormaPagamentoPorRestauranteId(@PathVariable(value = "restauranteId") Long restauranteId){
        CollectionModel<FormaPagamentoDTO> formaPagamentoModels = service.findAllFormasPagamentos(restauranteId);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoModels);
    }


    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @PutMapping(value = "/{formaPagamentoId}")
    public void associaFormaPagamentoDoRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "formaPagamentoId") Long formaPagamentoId) {

        service.associaFormaPagamento(restauranteId, formaPagamentoId);
    }


    @CheckSecurity.Restaurantes.PodeGerenciarFuncionamento
    @DeleteMapping(value = "/{formaPagamentoId}")
    public void desassociaFormaPagamentoDoRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "formaPagamentoId") Long formaPagamentoId) {

        service.desassociaFormaPagamento(restauranteId, formaPagamentoId);
    }
}
