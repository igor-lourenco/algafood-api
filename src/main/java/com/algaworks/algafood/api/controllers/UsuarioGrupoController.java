package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.domain.services.UsuarioGrupoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.UsuarioGrupoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos")
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioGrupoService usuarioGrupoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<UsuarioGrupoDTO>> lista(@PathVariable Long usuarioId) {
        CollectionModel<UsuarioGrupoDTO> usuarioDTOS = usuarioGrupoService.findGruposByUsuarioId(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }


    @PutMapping(value = "/{grupoId}")
    public void associa(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioGrupoService.associaUsuarioWithGrupo(usuarioId, grupoId);
    }


    @DeleteMapping(value = "/{grupoId}")
    public void desassocia(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioGrupoService.desassociaUsuarioWithGrupo(usuarioId, grupoId);
    }

}
