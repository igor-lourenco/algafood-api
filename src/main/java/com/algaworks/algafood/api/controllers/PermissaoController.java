package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.PermissaoDTO;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.services.PermissaoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.PermissaoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/permissoes")
public class PermissaoController implements PermissaoControllerOpenApi {

    @Autowired
    private PermissaoService permissaoService;


    @CheckSecurity.Permissao.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<PermissaoDTO>> lista() {
        CollectionModel<PermissaoDTO> grupoDTOS = permissaoService.findAllPermissoes();
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);
    }

}
