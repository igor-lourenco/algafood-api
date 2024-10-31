package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.domain.services.RestauranteUsuarioService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteUsuarioControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/usuarios", produces = {MediaType.APPLICATION_JSON_VALUE})
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {

    @Autowired
    private RestauranteUsuarioService service;

    @GetMapping
    public ResponseEntity<List<RestauranteUsuarioDTO>> buscaUsuarioPeloRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId){

        List<RestauranteUsuarioDTO> produtoDTOS = service.findUsuarioByRestauranteId(restauranteId);
        return ResponseEntity.status(HttpStatus.OK).body(produtoDTOS);
    }


    @PutMapping(value = "/{usuarioId}")
    public ResponseEntity<Void> associaUsuarioComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "usuarioId") Long usuarioId){

        service.associaRestauranteWithUsuario(restauranteId, usuarioId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(value = "/{usuarioId}")
    public ResponseEntity<Void> desassociaUsuarioComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "usuarioId") Long usuarioId){

        service.desassociaRestauranteWithUsuario(restauranteId, usuarioId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
