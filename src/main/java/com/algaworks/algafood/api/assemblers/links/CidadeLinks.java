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

    /** Cria link para o próprio objeto cidadeDTO*/
    public Link addSelfLink(CidadeDTO cidadeDTO) {

        return WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                .methodOn(CidadeController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeDTO.getId()))    //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF);
    }

    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

       return  WebMvcLinkBuilder          //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.           // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                methodOn(CidadeController.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                        //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION);  // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade
    }


    /** Cria link para a coleção do objeto estadoDTO desse objeto*/
    public Link addEstadoLink(CidadeDTO cidadeDTO){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador EstadoController
                .methodOn(EstadoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeDTO.getEstado().getId())) //  método do EstadoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso do estado dessa cidade
    }


    /** Cria link da URI mapeada na classe CidadeController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(CidadeController.class)
            .withSelfRel();
    }
}
