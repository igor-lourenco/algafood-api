package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.domain.services.RestauranteFormaPagamentoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteFormaPagamentoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/formas-pagamento")
public class RestauranteFormaPagamentoController implements RestauranteFormaPagamentoControllerOpenApi {

    @Autowired
    private RestauranteFormaPagamentoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FormaPagamentoDTO>> buscaFormaPagamentoPorRestauranteId(@PathVariable(value = "restauranteId") Long restauranteId){
        List<FormaPagamentoDTO> formaPagamentoModels = service.findAllFormasPagamentos(restauranteId);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoModels);
    }


    @PutMapping(value = "/{formaPagamentoId}")
    public void associaFormaPagamentoComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "formaPagamentoId") Long formaPagamentoId) {

        service.associaFormaPagamento(restauranteId, formaPagamentoId);
    }


    @DeleteMapping(value = "/{formaPagamentoId}")
    public void desassociaFormaPagamentoComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "formaPagamentoId") Long formaPagamentoId) {

        service.desassociaFormaPagamento(restauranteId, formaPagamentoId);
    }
}
