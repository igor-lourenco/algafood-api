package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.domain.services.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Produtos")
@RestController
@RequestMapping(path = "/v1/produtos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

//  A propriedade 'hidden = true' Oculta esse controlador na documentação do swagger
    @Operation(summary = "Busca produto pelo id", hidden = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        ProdutoDTO produtoDTO = produtoService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);

    }
}
