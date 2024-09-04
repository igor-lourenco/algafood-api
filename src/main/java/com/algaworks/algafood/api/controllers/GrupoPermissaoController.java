package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.domain.services.GrupoPermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class GrupoPermissaoController {

    @Autowired
    private GrupoPermissaoService grupoPermissaoService;

    @GetMapping
    public ResponseEntity<List<GrupoPermissaoDTO>> findAllPermissoes(@PathVariable(value = "grupoId") Long grupoId) {
        List<GrupoPermissaoDTO> grupoDTOS = grupoPermissaoService.findAllPermissoes(grupoId);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);

    }


    @GetMapping(value = "/{permissaoId}")
    public ResponseEntity<GrupoPermissaoDTO> findPermissaoByPermissaoId(
        @PathVariable(value = "grupoId") Long grupoId,
        @PathVariable(value = "permissaoId") Long permissaoId) {

        GrupoPermissaoDTO grupoDTOS = grupoPermissaoService.findPermissaoByPermissaoId(grupoId, permissaoId);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);

    }


    @PutMapping(value = "/{permissaoId}")
    public ResponseEntity<?> associaPermissao(
        @PathVariable(value = "grupoId") Long grupoId,
        @PathVariable(value = "permissaoId") Long permissaoId) {

        grupoPermissaoService.associaPermissao(grupoId, permissaoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @DeleteMapping(value = "/{permissaoId}")
    public ResponseEntity<?> desassociaPermissao(
        @PathVariable(value = "grupoId") Long grupoId,
        @PathVariable(value = "permissaoId") Long permissaoId) {

        grupoPermissaoService.desassociaPermissao(grupoId, permissaoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
