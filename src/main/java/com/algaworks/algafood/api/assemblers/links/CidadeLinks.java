package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.controllers.CidadeController;
import com.algaworks.algafood.api.controllers.EstadoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CidadeLinks {

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CidadeController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    public Link addSelfLink(CidadeDTO cidadeDTO) {
        return WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                .methodOn(CidadeController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeDTO.getId()))    //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF);
    }


    public Link addCollectionLink(){
       return  WebMvcLinkBuilder          //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.           // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                methodOn(CidadeController.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                        //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); // // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade
    }


    public Link addEstadoLink(CidadeDTO cidadeDTO){
       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                .methodOn(EstadoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeDTO.getEstado().getId())) //  método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso do estado dessa cidade
    }


//  Dessa forma adiciona a própria URI mapeada na classe CidadeController para essa coleção
    public Link addSelfCollection(){
        return WebMvcLinkBuilder
            .linkTo(CidadeController.class)
            .withSelfRel();
    }
}
