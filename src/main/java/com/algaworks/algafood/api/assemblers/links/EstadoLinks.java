package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.controllers.EstadoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class EstadoLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe EstadoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto */
    public Link addSelfLink(EstadoDTO estadoDTO){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                .methodOn(EstadoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(estadoDTO.getId())) // método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio estado
    }

    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                methodOn(EstadoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                  //  método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); //// Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do estado
    }


    /** Cria link da URI mapeada na classe EstadoController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(EstadoController.class)
            .withSelfRel();
    }
}
