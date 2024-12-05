package com.algaworks.algafood.api.controllers;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.domain.services.RootEntryPointService;
import com.algaworks.algafood.swaggerOpenApi.controllers.RootEntryPointControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController implements RootEntryPointControllerOpenApi {

    @Autowired
    private RootEntryPointService rootEntryPointService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RootEntryPointDTO> rootEntryPoint() {
        RootEntryPointDTO rootEntryPointDTO = rootEntryPointService.rootEntryPoint();
        return ResponseEntity.status(HttpStatus.OK).body(rootEntryPointDTO);
    }
}
