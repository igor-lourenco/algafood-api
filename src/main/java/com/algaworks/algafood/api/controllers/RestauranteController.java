package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.DTOs.jsonView.RestauranteViewDTO;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.models.views.RestauranteView;
import com.algaworks.algafood.domain.services.RestauranteService;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteComNomeSemelhanteSpec;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteSpecs;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/restaurantes", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @ApiImplicitParams({ // Informa na documentação dessa API, o campo implícito que o Squiggly usa para filtrar os campos que serão retornados
        @ApiImplicitParam(
            value = "Nomes das propriedades para filtrar na resposta, separados por vírgula",
            name = "apenasOsCampos", paramType = "query", type = "string")
    })
    @GetMapping
    public ResponseEntity<List<RestauranteDTO>> listar() {
        List<RestauranteDTO> restauranteDTOS = restauranteService.listar();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);

    }


    @GetMapping(value = "/{id}")
    public ResponseEntity<RestauranteDTO> buscaPorId(@PathVariable(value = "id") Long id) {
        RestauranteDTO entityDTO = restauranteService.buscaPorId(id);
        return ResponseEntity.status(HttpStatus.OK)
//            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)) // Tempo em segundos que pode ficar armazenado a resposta no cache do Browser
            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) // Indica que qualquer cache pode armazenar a resposta, é o padrão.
//            .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate()) // Indica que não é para armazenar em cache compartilhado no caminho entre o cliente e o servidor, apenas armazena no cache local do Browser do cliente
//            .cacheControl(CacheControl.noCache()) // Indica que o cache só pode ser reutilizada(ou seja, cache válido) se o cliente revalidar com o servidor.
//            .cacheControl(CacheControl.noStore()) //  Evita que os caches (navegadores e proxies) armazenem em cache o conteúdo das respostas.
            .body(entityDTO);

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


    @PutMapping(value = "/{restauranteId}/ativa")
    public ResponseEntity<RestauranteDTO> ativa(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.ativa(restauranteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @PutMapping(value = "/ativacoes")
    public ResponseEntity<RestauranteDTO> ativa(@RequestBody List<Long> restauranteIds) {
        restauranteService.ativa(restauranteIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @DeleteMapping(value = "/{restauranteId}/inativa")
    public ResponseEntity<RestauranteDTO> inativa(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.inativa(restauranteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @DeleteMapping(value = "/inativacoes")
    public ResponseEntity<RestauranteDTO> inativa(@RequestBody List<Long> restauranteIds) {
        restauranteService.inativa(restauranteIds);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

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


    @PutMapping(value = "/{restauranteId}/fechamento")
    public ResponseEntity<RestauranteDTO> fechamento(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.fechamento(restauranteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @PutMapping(value = "/{restauranteId}/abertura")
    public ResponseEntity<RestauranteDTO> abertura(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.abertura(restauranteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

/*  ==================================================================================
    ==== APIS de exemplo usando a annotation @JsonView para projeção dos atributos ===
    ================================================================================== */

    @JsonView(RestauranteView.Resumo.class)
    @GetMapping("/com-json-view")
//    @GetMapping(params = "resumo") // Outra alternativa seria usar o params em vez de criar uma rota nova
    public ResponseEntity<List<RestauranteViewDTO>> listarComJsonView() {
        List<RestauranteViewDTO> restauranteDTOS = restauranteService.listarComJsonView();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);

    }

    // Outra alterativa mas implementando o @JsonView dinamicamente sem precisar anotar a API diretamente
    @GetMapping("/com-json-view-wrapper")
    public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {

        List<RestauranteViewDTO> restauranteDTOS = restauranteService.listarComJsonView();
        MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restauranteDTOS);

        restaurantesWrapper.setSerializationView(null); // Se não especificar a projeção no @RequestParam retorna todos os campos por padrão

        if ("apenas-nome".equals(projecao)) {
            restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
        } else if ("resumo".equals(projecao)) {
            restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
        }

        return restaurantesWrapper;
    }

//  ==================================================================================
//  ==================================================================================

}
