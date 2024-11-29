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

}
