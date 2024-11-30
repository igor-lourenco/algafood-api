package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.api.controllers.UsuarioGrupoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe UsuarioController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto */
    public Link addSelfLink(UsuarioDTO usuarioDTO){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                .methodOn(UsuarioController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(usuarioDTO.getId())) // método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio usuario
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(String rel){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                methodOn(UsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                  //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuario
    }


    /** Cria link para a coleção de grupos desse usuario */
    public Link addSelfGruposUsuarioLink(UsuarioDTO usuarioDTO, String rel){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador UsuarioGrupoController
                .methodOn(UsuarioGrupoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista(usuarioDTO.getId())) //  método do UsuarioGrupoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); // Representa o URI indicando que este link aponta para o próprio recurso do grupo desse usuario
    }


    /** Cria link da URI mapeada na classe UsuarioController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(UsuarioController.class)
            .withSelfRel();
    }
}
