package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.domain.services.UsuarioGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UsuarioGrupoController {

    @Autowired
    private UsuarioGrupoService usuarioGrupoService;

    @GetMapping
    public ResponseEntity<List<UsuarioGrupoDTO>> listar(@PathVariable Long usuarioId) {
        List<UsuarioGrupoDTO> usuarioDTOS = usuarioGrupoService.findGruposByUsuarioId(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);

    }

    @PutMapping(value = "/{grupoId}")
    public ResponseEntity<?> associa(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioGrupoService.associaUsuarioWithGrupo(usuarioId, grupoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @DeleteMapping(value = "/{grupoId}")
    public ResponseEntity<?> desassocia(@PathVariable Long usuarioId, @PathVariable Long grupoId){
        usuarioGrupoService.desassociaUsuarioWithGrupo(usuarioId, grupoId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
