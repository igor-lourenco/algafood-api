package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.inputs.UsuarioComSenhaInput;
import com.algaworks.algafood.api.inputs.UsuarioInput;
import com.algaworks.algafood.api.inputs.UsuarioNovaSenhaInput;
import com.algaworks.algafood.domain.services.UsuarioService;
import com.algaworks.algafood.swaggerOpenApi.controllers.UsuarioControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController implements UsuarioControllerOpenApi {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> lista() {
        List<UsuarioDTO> usuarioDTOS = usuarioService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTOS);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> buscaPorId(@PathVariable(value = "id") Long id){
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> buscaPorNome(@RequestParam(value = "nome") String nome) {
        List<UsuarioDTO> formaPagamentoDTOS = usuarioService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoDTOS);
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


    @PutMapping(value = "/{id}/senha", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> alteraSenha(@PathVariable(value = "id") Long id, @Valid @RequestBody UsuarioNovaSenhaInput usuarioInput){
        usuarioService.alteraSenha(id, usuarioInput);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id){
        usuarioService.deleta(id);
    }
}
