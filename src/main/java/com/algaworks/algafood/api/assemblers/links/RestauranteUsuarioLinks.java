package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.api.controllers.RestauranteUsuarioController;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

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


    /** Cria link para desassociar o restaurante desse usuário*/
    public Link addDesassociaRestauranteDoUsuarioLink(Long restauranteId, Long responsavelId) {
        try {

            Class<?> controllerClass = RestauranteUsuarioController.class;
            Method method = controllerClass.getMethod("desassociaUsuarioDoRestaurante", Long.class, Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, restauranteId, responsavelId)   // é usado para referenciar um controlador e um método específico de forma segura.
                .withRel("desassociar"); // Representa o URI indicando que este link desassocia o restaurante dessa forma de pagamento

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método desassociaUsuarioDoRestaurante(Long restauranteId, Long responsavelId) da classe RestauranteUsuarioController");
        }
    }


    /** Cria link para associar o restaurante com usuário*/
    public Link addAssociaRestauranteDoUsuarioLink(Long restauranteId) {

        String urlRestauranteUsuario =
            WebMvcLinkBuilder.linkTo(RestauranteUsuarioController.class, restauranteId)
                .toUriComponentsBuilder().toUriString();

        String urlComTemplate = urlRestauranteUsuario + "/{responsavelId}";

        return Link.of(
            UriTemplate.of(urlComTemplate),
            "associar"
        );
    }
}
