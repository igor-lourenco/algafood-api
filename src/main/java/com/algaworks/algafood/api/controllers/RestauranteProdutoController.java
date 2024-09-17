package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.domain.services.RestauranteProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteProdutoController {

    @Autowired
    private RestauranteProdutoService service;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(@RequestParam(required = false) boolean incluirInativos,
        @PathVariable(value = "restauranteId") Long restauranteId){

        List<ProdutoDTO> produtoDTOS;
        produtoDTOS = incluirInativos ? service.findAllProdutos(restauranteId) : service.findAllProdutosAtivos(restauranteId);

        return ResponseEntity.status(HttpStatus.OK).body(produtoDTOS);

    }


    @GetMapping(value = "/{produtoId}")
    public ResponseEntity<ProdutoDTO> findByProdutoId(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "produtoId") Long produtoId){

        ProdutoDTO produtoDTO = service.findAllProdutosById(restauranteId, produtoId);

        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);

    }


    @PutMapping(value = "/{produtoId}")
    public ResponseEntity<ProdutoDTO> saveProduto(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "produtoId") Long produtoId,
        @Valid @RequestBody ProdutoInput produtoInput){

        ProdutoDTO produtoDTO = service.alteraProdutoByRestauranteId(restauranteId, produtoId, produtoInput);

        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);

    }
}
