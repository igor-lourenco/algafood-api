package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.CozinhaDTO;
import com.algaworks.algafood.api.controllers.CozinhaController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CozinhaLinks {

    /** Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CozinhaController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto */
    public Link addSelfLink(CozinhaDTO cozinhaDTO){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador CozinhaController
                .methodOn(CozinhaController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cozinhaDTO.getId())) // //  método do CozinhaController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para a própria cozinha
    }

    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador CozinhaController
                methodOn(CozinhaController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do CozinhaController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); //// Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cozinha
    }


    /** Cria link da URI mapeada na classe CozinhaController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(CozinhaController.class)
            .withSelfRel();
    }
}
