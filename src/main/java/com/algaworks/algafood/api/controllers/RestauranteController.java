package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.core.constraints.groups.Groups;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
            @Validated(Groups.CadastroRestaurante.class)
            @RequestBody RestauranteModel restauranteModel) {

        RestauranteDTO restauranteDTO = restauranteService.salvar(restauranteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteDTO);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<RestauranteDTO> alterar(@PathVariable(value = "id") Long id, @RequestBody RestauranteModel restauranteModel) {
        RestauranteDTO restauranteDTO = restauranteService.alterar(id, restauranteModel);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTO);

    }

//    @PatchMapping(value = "/{id}")
//    public ResponseEntity<?> alterarParcial(
//            @PathVariable(value = "id") Long id,
//            @RequestBody Map<String, Object> campos,
//            HttpServletRequest request) {
//
//        RestauranteModel obj = restauranteService.alterarParcial(id, campos, request);
//        return ResponseEntity.status(HttpStatus.OK).body(obj);
//
//    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<RestauranteModel> deletar(@PathVariable(value = "id") Long id) {
        restauranteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @GetMapping(value = "/com-frete-gratis")
    public ResponseEntity<?> buscaRestauranteComFreteGratis(@RequestParam(value = "nome") String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        Specification<RestauranteModel> restauranteSpecs = RestauranteSpecs.comFreteGratis().
                and(RestauranteSpecs.comNomeSemelhante(nome));

        List<RestauranteModel> restauranteModels = restauranteService.findAll(restauranteSpecs);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteModels);

    }
}
