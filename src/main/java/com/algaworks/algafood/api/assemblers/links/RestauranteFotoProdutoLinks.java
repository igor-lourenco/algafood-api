package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.controllers.RestauranteProdutoController;
import com.algaworks.algafood.api.controllers.RestauranteProdutoFotoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestauranteFotoProdutoLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe RestauranteProdutoFotoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */


    /** Cria link para o próprio objeto */
    public Link addSelfLink(Long restauranteId, Long produtoId){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoFotoController
                .methodOn(RestauranteProdutoFotoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .recuperaDadosFoto(restauranteId, produtoId)) // método do RestauranteProdutoFotoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para a própria foto
    }


    /** Cria link para o produto do restaurante dessa foto*/
    public Link addRestauranteProdutoLink(Long restauranteId, Long produtoId){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                methodOn(RestauranteProdutoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaProdutoPeloId(restauranteId, produtoId))     //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("produto"); // Representa o URI do recurso do produto do restaurante dessa foto
    }

}
