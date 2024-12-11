package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.CozinhaDTOV2;
import com.algaworks.algafood.api.controllersV2.CozinhaControllerV2;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class CozinhaLinksV2 {

    /** Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CozinhaControllerV2 com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto */
    public Link addSelfLink(CozinhaDTOV2 cozinhaDTO){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador CozinhaControllerV2
                .methodOn(CozinhaControllerV2.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cozinhaDTO.getCidadeId())) // //  método do CozinhaControllerV2 para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para a própria cozinha
    }

    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(String rel){

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador CozinhaControllerV2
                methodOn(CozinhaControllerV2.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do CozinhaControllerV2 para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cozinha
    }


    /** Cria link da URI mapeada na classe CozinhaControllerV2 para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(CozinhaControllerV2.class)
            .withSelfRel();
    }
}
