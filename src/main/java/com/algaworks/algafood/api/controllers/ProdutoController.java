package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.domain.services.ProdutoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Produtos")
@RestController
@RequestMapping(path = "/v1/produtos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

//  A propriedade 'hidden = true' Oculta esse controlador na documentação do swagger
    @ApiOperation(value = "Busca produto pelo id", hidden = true)
    @GetMapping(value = "/{id}")
    public ResponseEntity<ProdutoDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        ProdutoDTO produtoDTO = produtoService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTO);

    }
}
