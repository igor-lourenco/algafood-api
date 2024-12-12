package com.algaworks.algafood.api.controllersV2;

import com.algaworks.algafood.api.DTOs.RootEntryPointDTO;
import com.algaworks.algafood.domain.services.RootEntryPointServiceV2;
import com.algaworks.algafood.swaggerOpenApi.controllers.RootEntryPointControllerOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v2", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointControllerV2 implements RootEntryPointControllerOpenApi {

    @Autowired
    private RootEntryPointServiceV2 rootEntryPointService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RootEntryPointDTO> rootEntryPoint() {
        RootEntryPointDTO rootEntryPointDTO = rootEntryPointService.rootEntryPointV2();
        return ResponseEntity.status(HttpStatus.OK).body(rootEntryPointDTO);
    }
}
