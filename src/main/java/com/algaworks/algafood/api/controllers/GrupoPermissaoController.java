package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.domain.services.GrupoPermissaoService;
import com.algaworks.algafood.swaggerOpenApi.controllers.GrupoPermissaoControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissaoController implements GrupoPermissaoControllerOpenApi {

    @Autowired
    private GrupoPermissaoService grupoPermissaoService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<GrupoPermissaoDTO>> buscaTodasPermissoesDoGrupo(@PathVariable(value = "grupoId") Long grupoId) {
        List<GrupoPermissaoDTO> grupoDTOS = grupoPermissaoService.findAllPermissoes(grupoId);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);
    }


    @GetMapping(value = "/{permissaoId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GrupoPermissaoDTO> buscaPermissaoDoGrupoPeloId(
        @PathVariable(value = "grupoId") Long grupoId,
        @PathVariable(value = "permissaoId") Long permissaoId) {

        GrupoPermissaoDTO grupoDTOS = grupoPermissaoService.findPermissaoByPermissaoId(grupoId, permissaoId);
        return ResponseEntity.status(HttpStatus.OK).body(grupoDTOS);
    }


    @PutMapping(value = "/{permissaoId}")
    public void associaPermissao(
        @PathVariable(value = "grupoId") Long grupoId,
        @PathVariable(value = "permissaoId") Long permissaoId) {

        grupoPermissaoService.associaPermissao(grupoId, permissaoId);
    }


    @DeleteMapping(value = "/{permissaoId}")
    public void desassociaPermissao(
        @PathVariable(value = "grupoId") Long grupoId,
        @PathVariable(value = "permissaoId") Long permissaoId) {

        grupoPermissaoService.desassociaPermissao(grupoId, permissaoId);
    }
}
