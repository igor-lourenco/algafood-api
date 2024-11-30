package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.controllers.PermissaoController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class PermissaoLinks {

    /** Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe PermissaoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link da URI mapeada na classe PermissaoController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(PermissaoController.class)
            .withSelfRel();
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(String rel){

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador PermissaoController
                methodOn(PermissaoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do PermissaoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); //// Representa o URI para a coleção de recursos do mesmo tipo do recurso atual
    }

}
