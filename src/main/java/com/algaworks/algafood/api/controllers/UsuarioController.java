package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.domain.services.UsuarioService;
import com.algaworks.algafood.swaggerOpenApi.controllers.UsuarioControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/v1/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<UsuarioDTO>> lista() {
        CollectionModel<UsuarioDTO> usuarioDTOS = usuarioService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> buscaPorId(@PathVariable(value = "id") Long id){
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<UsuarioDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        CollectionModel<UsuarioDTO> usuarioDTOS = usuarioService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> salva(@Valid @RequestBody UsuarioComSenhaInput usuarioInput){
        UsuarioDTO usuarioDTO = usuarioService.salva(usuarioInput);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> altera(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioInput usuarioInput){
        UsuarioDTO usuarioDTO = usuarioService.altera(id, usuarioInput);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @PutMapping(value = "/{id}/senha")
    public void alteraSenha(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioNovaSenhaInput usuarioInput){
        usuarioService.alteraSenha(id, usuarioInput);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id){
        usuarioService.deleta(id);
    }
}
