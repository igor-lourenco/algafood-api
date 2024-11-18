package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.api.controllers.RestauranteUsuarioController;
import com.algaworks.algafood.api.controllers.UsuarioController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestauranteUsuarioLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe UsuarioController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */


    /** Cria link para o próprio objeto */
    public Link addSelfLink(RestauranteUsuarioDTO entityDTO){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                .methodOn(UsuarioController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(entityDTO.getId())) // método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio usuario
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                methodOn(UsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                  //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuario
    }



    /** Cria link para a coleção de responsáveis desse restaurante */
    public Link addSelfRestauranteResponsaveisLink(Long restauranteId){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador RestauranteUsuarioController
                methodOn(RestauranteUsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaUsuarioPeloRestaurante(restauranteId))   //  método do RestauranteUsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do restaurante-responsaveis
    }


    /** Cria link da URI mapeada na classe UsuarioController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(UsuarioController.class)
            .withSelfRel();
    }
}
