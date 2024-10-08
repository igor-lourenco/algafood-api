package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.inputs.GrupoInput;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.domain.services.GrupoService;
import com.algaworks.algafood.domain.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> usuarioDTOS = usuarioService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable(value = "id") Long id){
        UsuarioDTO usuarioDTO = usuarioService.findById(id);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @GetMapping(value = "/consulta-por-nome")
    public ResponseEntity<List<UsuarioDTO>> findByNome(@RequestParam(value = "nome") String nome) {
        List<UsuarioDTO> formaPagamentoDTOS = usuarioService.consultaPorNome(nome);

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);

    }


    @PostMapping
    public ResponseEntity<UsuarioDTO> salva(@Valid @RequestBody UsuarioComSenhaInput usuarioInput){
        UsuarioDTO usuarioDTO = usuarioService.salva(usuarioInput);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);

    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioInput usuarioInput){
        UsuarioDTO usuarioDTO = usuarioService.altera(id, usuarioInput);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);

    }


    @PutMapping(value = "/{id}/senha")
    public ResponseEntity<UsuarioDTO> alteraSenha(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioNovaSenhaInput usuarioInput){
        usuarioService.alteraSenha(id, usuarioInput);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleta(@PathVariable(value = "id") Long id){
        usuarioService.deleta(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
