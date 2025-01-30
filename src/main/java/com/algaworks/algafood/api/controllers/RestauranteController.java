package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.DTOs.jsonView.RestauranteViewDTO;
import com.algaworks.algafood.api.inputs.RestauranteInput;
import com.algaworks.algafood.core.security.CheckSecurity;
import com.algaworks.algafood.domain.models.RestauranteModel;
import com.algaworks.algafood.domain.models.views.RestauranteView;
import com.algaworks.algafood.domain.services.RestauranteService;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteComFreteGratisSpec;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteComNomeSemelhanteSpec;
import com.algaworks.algafood.infrastructure.repositories.specs.RestauranteSpecs;
import com.algaworks.algafood.swaggerOpenApi.controllers.RestauranteControllerOpenApi;
import com.algaworks.algafood.swaggerOpenApi.models.RestauranteParcialModelOpenApi;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.hateoas.CollectionModel;
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
@RequestMapping(path = "/v1/restaurantes")
public class RestauranteController implements RestauranteControllerOpenApi {

    @Autowired
    private RestauranteService restauranteService;


    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<RestauranteDTO>> lista() {
        CollectionModel<RestauranteDTO> restauranteDTOS = restauranteService.lista();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);
    }


    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
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


    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(value = "/com-frete-gratis", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<RestauranteDTO>> buscaRestaurantesComFreteGratis(@RequestParam(value = "nome") String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpec();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpec(nome);

        Specification<RestauranteModel> restauranteSpecs = RestauranteSpecs.comFreteGratis().
            and(RestauranteSpecs.comNomeSemelhante(nome));

        CollectionModel<RestauranteDTO> restauranteModels = restauranteService.findAllSpec(restauranteSpecs);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteModels);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestauranteDTO> salva(
        @Valid @RequestBody RestauranteInput restauranteInput) {

        RestauranteDTO restauranteDTO = restauranteService.salva(restauranteInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(restauranteDTO);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestauranteDTO> altera(
        @PathVariable(value = "id") Long id, @Valid @RequestBody RestauranteInput restauranteInput) {

        RestauranteDTO restauranteDTO = restauranteService.altera(id, restauranteInput);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTO);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(value = "/{restauranteId}/ativa")
    public void ativa(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.ativa(restauranteId);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(value = "/ativacoes")
    public void ativacoes(@RequestBody List<Long> restauranteIds) {
        restauranteService.ativa(restauranteIds);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping(value = "/{restauranteId}/inativa")
    public void inativa(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.inativa(restauranteId);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping(value = "/inativacoes")
    public void inativacoes(@RequestBody List<Long> restauranteIds) {
        restauranteService.inativa(restauranteIds);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PatchMapping(value = "/{id}",
        produces = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE}, // para diferenciar na visualização da documentação
        consumes = {MediaType.ALL_VALUE, MediaType.APPLICATION_JSON_VALUE})// para diferenciar na visualização da documentação
    public ResponseEntity<RestauranteDTO> alteraParcial(
        @PathVariable(value = "id") Long id,
        @RequestBody Map<String, Object> campos,
        HttpServletRequest request) {

        RestauranteDTO restauranteDTO = restauranteService.alteraParcial(id, campos, request);
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTO);

    }


    @CheckSecurity.Restaurantes.PodeEditar
    @DeleteMapping(value = "/{id}")
    public void deleta(@PathVariable(value = "id") Long id) {
        restauranteService.deleta(id);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(value = "/{restauranteId}/fechamento")
    public void fechamento(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.fechamento(restauranteId);
    }


    @CheckSecurity.Restaurantes.PodeEditar
    @PutMapping(value = "/{restauranteId}/abertura")
    public void abertura(@PathVariable(value = "restauranteId") Long restauranteId) {
        restauranteService.abertura(restauranteId);
    }

/*  ==================================================================================
    ==== APIS de exemplo usando a annotation @JsonView para projeção dos atributos ===
    ================================================================================== */

    @CheckSecurity.Restaurantes.PodeConsultar
    @JsonView(RestauranteView.Resumo.class)
    @GetMapping(value = "/com-json-view", produces = MediaType.APPLICATION_JSON_VALUE)
//    @GetMapping(params = "resumo") // Outra alternativa seria usar o params em vez de criar uma rota nova
    public ResponseEntity<List<RestauranteViewDTO>> listarComJsonView() {
        List<RestauranteViewDTO> restauranteDTOS = restauranteService.listarComJsonView();
        return ResponseEntity.status(HttpStatus.OK).body(restauranteDTOS);
    }


    // Outra alterativa mas implementando o @JsonView dinamicamente sem precisar anotar a API diretamente
    @CheckSecurity.Restaurantes.PodeConsultar
    @GetMapping(value = "/com-json-view-wrapper", produces = MediaType.APPLICATION_JSON_VALUE)
    public MappingJacksonValue listaComWrapper(@RequestParam(required = false) String projecao) {

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


    /** Essa API foi criada apenas para visualização customizada na documentação simulando a API - PATCH altera parcialmente Restaurante*/
    @PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestauranteDTO> alteraParcialSwagger(
        @PathVariable(value = "id") Long id,
        @RequestBody RestauranteParcialModelOpenApi restauranteInput) { return null; }

}
