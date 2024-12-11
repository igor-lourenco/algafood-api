package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.api.controllersV2.CidadeControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CidadeLinksV2 {

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CidadeControllerV2 com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto dtov2*/
    public Link addSelfLink(CidadeDTOV2 dtov2) {

        return WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador CidadeControllerV2
                .methodOn(CidadeControllerV2.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(dtov2.getId()))    //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF);
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(String rel){

       return  WebMvcLinkBuilder          //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.           // cria uma base para o link HATEOAS, apontando para o controlador CidadeControllerV2
                methodOn(CidadeControllerV2.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                        //  método do CidadeControllerV2 para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel);  // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade
    }


    /** Cria link da URI mapeada na classe CidadeControllerV2 para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(CidadeControllerV2.class)
            .withSelfRel();
    }
}
