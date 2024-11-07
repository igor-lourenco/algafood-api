package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.domain.services.UsuarioGrupoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.UsuarioGrupoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UsuarioGrupoController implements UsuarioGrupoControllerOpenApi {

    @Autowired
    private UsuarioGrupoService usuarioGrupoService;

    @GetMapping
    public ResponseEntity<List<UsuarioGrupoDTO>> lista(@PathVariable Long usuarioId) {
        List<UsuarioGrupoDTO> usuarioDTOS = usuarioGrupoService.findGruposByUsuarioId(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }


    @PutMapping(value = "/{grupoId}")
    public ResponseEntity<Void> associa(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioGrupoService.associaUsuarioWithGrupo(usuarioId, grupoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(value = "/{grupoId}")
    public ResponseEntity<Void> desassocia(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioGrupoService.desassociaUsuarioWithGrupo(usuarioId, grupoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
