package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.controllers.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestauranteLinks {

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe CidadeController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto restauranteDTO */
    public Link addSelfLink(RestauranteDTO restauranteDTO) {

        return WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador RestauranteController
                .methodOn(RestauranteController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(restauranteDTO.getId()))    //  método do RestauranteController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio restaurante
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

       return  WebMvcLinkBuilder          //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.           // cria uma base para o link HATEOAS, apontando para o controlador RestauranteController
                methodOn(RestauranteController.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                        //  método do RestauranteController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION);  // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do restaurante
    }


    /** Cria link para a coleção do objeto cozinha desse objeto*/
    public Link addSelfCozinhaLink(RestauranteDTO restauranteDTO){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador CozinhaController
                .methodOn(CozinhaController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(restauranteDTO.getCozinha().getId())) //  método do CozinhaController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso da cozinha  desse restaurante
    }


    /** Cria link para a cidade desse restaurante */
    public Link addSelfCidadeLink(RestauranteDTO restauranteDTO){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                .methodOn(CidadeController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(restauranteDTO.getEndereco().getCidade().getId())) //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso de cidade desse restaurante
    }


    /** Cria link para as formas de pagamento desse restaurante */
    public Link addSelfFormasDePagamentoLink(RestauranteDTO restauranteDTO){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador RestauranteFormaPagamentoController
                .methodOn(RestauranteFormaPagamentoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaFormaPagamentoPorRestauranteId(restauranteDTO.getId())) //  método do RestauranteFormaPagamentoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("formas-pagamento"); // Representa o URI indicando que este link aponta para o próprio recurso de formas de pagamento desse restaurante
    }


    /** Cria link para os usuários responsáveis desse restaurante */
    public Link addSelfUsuariosResponsaveisLink(RestauranteDTO restauranteDTO){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador RestauranteUsuarioController
                .methodOn(RestauranteUsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaUsuarioPeloRestaurante(restauranteDTO.getId())) //  método do RestauranteUsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("responsaveis"); // Representa o URI indicando que este link aponta para o próprio recurso de usuarios responsaveis desse restaurante
    }


    /** Cria link da URI mapeada na classe RestauranteController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(RestauranteController.class)
            .withSelfRel();
    }
}
