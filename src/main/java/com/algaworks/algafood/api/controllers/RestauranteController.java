package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.services.RestauranteService;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteComNomeSemelhanteSpec;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<RestauranteDTO> restauranteDTOS = restauranteService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        RestauranteDTO entityDTO = restauranteService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(entityDTO);

    }

    @PostMapping
    public ResponseEntity<RestauranteDTO> salvar(
            @Valid @RequestBody RestauranteInput restauranteInput) {

        RestauranteDTO restauranteDTO = restauranteService.salvar(restauranteInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RestauranteDTO> alterar(
        @PathVariable(value = "id") Long id, @Valid @RequestBody RestauranteInput restauranteInput) {

        RestauranteDTO restauranteDTO = restauranteService.alterar(id, restauranteInput);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTO);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<RestauranteDTO> alterarParcial(
            @PathVariable(value = "id") Long id,
            @RequestBody Map<String, Object> campos,
            HttpServletRequest request) {

        RestauranteDTO restauranteDTO = restauranteService.alterarParcial(id, campos, request);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTO);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RestauranteDTO> deletar(@PathVariable(value = "id") Long id) {
        restauranteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @GetMapping(value = "/com-frete-gratis")
    public ResponseEntity<List<RestauranteDTO>> buscaRestauranteComFreteGratis(@RequestParam(value = "nome") String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        Specification<RestauranteModel> restauranteSpecs = RestauranteSpecs.comFreteGratis().
                and(RestauranteSpecs.comNomeSemelhante(nome));

        List<RestauranteDTO> restauranteModels = restauranteService.findAllSpec(restauranteSpecs);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteModels);

    }
}
