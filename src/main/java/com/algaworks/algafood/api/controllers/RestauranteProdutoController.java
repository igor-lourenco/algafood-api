package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.inputs.ProdutoInput;
import com.algaworks.algafood.domain.services.RestauranteProdutoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteProdutoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController implements RestauranteProdutoControllerOpenApi {

    @Autowired
    private RestauranteProdutoService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<ProdutoDTO>> buscaTodosProdutosDoRestaurante(
        @RequestParam(required = false, defaultValue = "false") Boolean incluirInativos,
        @PathVariable(value = "restauranteId") Long restauranteId){

        CollectionModel<ProdutoDTO> produtoDTOS;
        produtoDTOS = incluirInativos ? service.findAllProdutos(restauranteId) : service.findAllProdutosAtivos(restauranteId);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTOS);
    }


    @GetMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoDTO> buscaProdutoPeloId(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "produtoId") Long produtoId){

        ProdutoDTO produtoDTO = service.findProdutoById(restauranteId, produtoId);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoDTO> salvaProdutoNoRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @Valid @RequestBody ProdutoInput produtoInput){

        ProdutoDTO produtoDTO = service.saveProdutoByRestauranteId(restauranteId, produtoInput);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);
    }


    @PutMapping(value = "/{produtoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoDTO> alteraProdutoDoRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "produtoId") Long produtoId,
        @Valid @RequestBody ProdutoInput produtoInput){

        ProdutoDTO produtoDTO = service.alteraProdutoByRestauranteId(restauranteId, produtoId, produtoInput);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);
    }
}
