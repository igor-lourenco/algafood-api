package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.services.RestauranteUsuarioService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteUsuarioControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {

    @Autowired
    private RestauranteUsuarioService service;

    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<RestauranteUsuarioDTO>> buscaUsuarioPeloRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId){

        CollectionModel<RestauranteUsuarioDTO> entityDTOS = service.findUsuarioByRestauranteId(restauranteId);
        return ResponseEntity.status(HttpStatus.OK).body(entityDTOS);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(value = "/{usuarioId}")
    public void associaUsuarioComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "usuarioId") Long usuarioId){

        service.associaRestauranteWithUsuario(restauranteId, usuarioId);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping(value = "/{usuarioId}")
    public void desassociaUsuarioDoRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "usuarioId") Long usuarioId){

        service.desassociaRestauranteWithUsuario(restauranteId, usuarioId);
    }
}
