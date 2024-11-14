package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.domain.services.RestauranteUsuarioService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteUsuarioControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioController implements RestauranteUsuarioControllerOpenApi {

    @Autowired
    private RestauranteUsuarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<RestauranteUsuarioDTO>> buscaUsuarioPeloRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId){

        CollectionModel<RestauranteUsuarioDTO> entityDTOS = service.findUsuarioByRestauranteId(restauranteId);

        entityDTOS.removeLinks() // remove o link para podermos adicionar o link com a URI mapeada desse método
            .add(WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(RestauranteUsuarioController.class)
                .buscaUsuarioPeloRestaurante(restauranteId))
            .withRel(IanaLinkRelations.SELF));

        return ResponseEntity.status(HttpStatus.OK).body(entityDTOS);
    }


    @PutMapping(value = "/{usuarioId}")
    public void associaUsuarioComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "usuarioId") Long usuarioId){

        service.associaRestauranteWithUsuario(restauranteId, usuarioId);
    }


    @DeleteMapping(value = "/{usuarioId}")
    public void desassociaUsuarioComRestaurante(
        @PathVariable(value = "restauranteId") Long restauranteId,
        @PathVariable(value = "usuarioId") Long usuarioId){

        service.desassociaRestauranteWithUsuario(restauranteId, usuarioId);
    }
}
