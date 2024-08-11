package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.domain.exceptions.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.models.CozinhaModel;
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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @GetMapping
    public ResponseEntity<List<RestauranteModel>> listar() {
        return ResponseEntity.status(HttpStatus.OK).body(restauranteService.listar());

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> buscaPorId(@PathVariable(value = "id") Long id) {
        RestauranteModel restauranteModel = restauranteService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteModel);

    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody RestauranteModel restauranteModel) {
        restauranteModel = restauranteService.salvar(restauranteModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteModel);

    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> alterar(@PathVariable(value = "id") Long id, @RequestBody RestauranteModel restauranteModel) {
        RestauranteModel obj = restauranteService.alterar(id, restauranteModel);
        return ResponseEntity.status(HttpStatus.OK).body(obj);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> alterarParcial(@PathVariable(value = "id") Long id, @RequestBody Map<String, Object> campos, HttpServletRequest request) {
        RestauranteModel obj = restauranteService.alterarParcial(id, campos, request);
        return ResponseEntity.status(HttpStatus.OK).body(obj);

    }

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
