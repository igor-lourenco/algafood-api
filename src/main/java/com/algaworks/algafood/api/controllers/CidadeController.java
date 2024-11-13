package com.algaworks.algafood.api.controllers;


import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.inputs.CidadeInput;
import com.algaworks.algafood.domain.services.CidadeService;
import com.algaworks.algafood.swaggerOpenApi.controllers.CidadeControllerOpenApi;
import com.algaworks.algafood.utils.ControllerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/cidades")
public class CidadeController implements CidadeControllerOpenApi {

    @Autowired
    private CidadeService cidadeService;


    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CollectionModel<CidadeDTO>> lista() {
        List<CidadeDTO> cidadeDTOS = cidadeService.listar();

//      Dessa forma usa methodOn() para referenciar diretamente o método buscaPorId da classe CidadeController com o ID especificado para cada cidade.
        cidadeDTOS.forEach(cidadeDTO ->
            cidadeDTO.add(WebMvcLinkBuilder
                .linkTo(WebMvcLinkBuilder
                .methodOn(CidadeController.class)
                .buscaPorId(cidadeDTO.getId()))
            .withRel(IanaLinkRelations.SELF)));

        CollectionModel<CidadeDTO> cidadeDTOCollectionModel = new CollectionModel<>(cidadeDTOS);

        cidadeDTOCollectionModel.add(WebMvcLinkBuilder
            .linkTo(CidadeController.class)
            .withSelfRel());


        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTOCollectionModel);
    }


    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> buscaPorId(
        @PathVariable(value = "id") Long id) {

        CidadeDTO cidadeDTO = cidadeService.buscaPorId(id);

//        Dessa forma o link é construído manualmente adicionando o ID do recurso com .slash(cidadeDTO.getId())
//        cidadeDTO.add(WebMvcLinkBuilder
//            .linkTo(CidadeController.class)    // Cria um novo WebMvcLinkBuilder com uma base do mapeamento anotada para a classe do controlador fornecida.
//            .slash(cidadeDTO.getId())          // Adiciona o sub-recurso ao URI atual, no caso seria o /{id}.
//            .withRel(IanaLinkRelations.SELF)); // Representa o URI para o próprio recurso atual da cidade.

//        Dessa forma o link é construído manualmente
//        cidadeDTO.add(WebMvcLinkBuilder
//            .linkTo(CidadeController.class)          // Cria um novo WebMvcLinkBuilder com uma base do mapeamento anotada para a classe do controlador fornecida.
//            .withRel(IanaLinkRelations.COLLECTION)); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade

//        Dessa forma o link é construído manualmente adicionando o ID do recurso com .slash(cidadeDTO.getEstado().getId())
//        cidadeDTO.getEstado().add(WebMvcLinkBuilder
//            .linkTo(EstadoController.class)       // Cria um novo WebMvcLinkBuilder com uma base do mapeamento anotada para a classe do controlador fornecida.
//            .slash(cidadeDTO.getEstado().getId()) // Adiciona o sub-recurso ao URI atual, no caso seria o /{id}.
//            .withRel(IanaLinkRelations.SELF));    // Representa o URI para o próprio recurso atual do estado.

//      Dessa forma usa methodOn() para referenciar diretamente o método buscaPorId da classe CidadeController com o ID especificado. Ajuda a evitar problemas caso a URL do método mude
        cidadeDTO.add(WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(CidadeController.class)
                .buscaPorId(cidadeDTO.getId()))
            .withRel(IanaLinkRelations.SELF));


        cidadeDTO.add(WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder.
                methodOn(CidadeController.class)
                .lista())
            .withRel(IanaLinkRelations.COLLECTION));


        cidadeDTO.getEstado().add(WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(EstadoController.class)
                .buscaPorId(cidadeDTO.getEstado().getId()))
            .withRel(IanaLinkRelations.SELF));

        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @GetMapping(value = "/consulta-por-nome", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CidadeDTO>> buscaPorNome(
        @RequestParam(value = "nome") String nome) {

        List<CidadeDTO> listaCidadePorNome = cidadeService.consultaPorNome(nome);
        return ResponseEntity.status(HttpStatus.OK).body(listaCidadePorNome);
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> salva(
        @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.salvar(cidadeInput);

//      Adiciona no cabeçalho de retorno o campo 'Location' com a URI do novo recurso criado
        ControllerUtils.adicionaUriNoHeaderDaResposta(cidadeDTO.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(cidadeDTO);
    }


    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CidadeDTO> altera(
        @PathVariable(value = "id") Long id,
        @Valid @RequestBody CidadeInput cidadeInput) {

        CidadeDTO cidadeDTO = cidadeService.alterar(id, cidadeInput);
        return ResponseEntity.status(HttpStatus.OK).body(cidadeDTO);
    }


    @DeleteMapping(value = "/{id}")
    public void deleta( @PathVariable(value = "id") Long id) {

        cidadeService.deletar(id);
    }

}
