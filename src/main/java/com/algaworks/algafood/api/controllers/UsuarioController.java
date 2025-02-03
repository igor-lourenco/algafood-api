package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.core.security.CheckSecurity;
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


    @CheckSecurity.Usuarios.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<UsuarioDTO>> lista() {
        CollectionModel<UsuarioDTO> usuarioDTOS = usuarioService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }


    @CheckSecurity.Usuarios.PodeConsultar
    @GetMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> buscaPorId(@PathVariable(value = "usuarioId") Long usuarioId){
        UsuarioDTO usuarioDTO = usuarioService.findById(usuarioId);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @CheckSecurity.Usuarios.PodeConsultar
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


    @CheckSecurity.Usuarios.PodeAlterarUsuario
    @PutMapping(value = "/{usuarioId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> altera(@PathVariable(value = "usuarioId") Long usuarioId, @Valid @RequestBody UsuarioInput usuarioInput){
        UsuarioDTO usuarioDTO = usuarioService.altera(usuarioId, usuarioInput);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @CheckSecurity.Usuarios.PodeAlterarPropriaSenha
    @PutMapping(value = "/{usuarioId}/senha")
    public void alteraSenha(@PathVariable(value = "usuarioId") Long usuarioId, @Valid @RequestBody UsuarioNovaSenhaInput usuarioInput){
        usuarioService.alteraSenha(usuarioId, usuarioInput);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @CheckSecurity.Usuarios.PodeEditar
    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id){
        usuarioService.deleta(id);
    }
}
