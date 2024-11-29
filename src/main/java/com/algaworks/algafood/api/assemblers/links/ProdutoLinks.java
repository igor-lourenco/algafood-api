package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.ProdutoDTO;
import com.algaworks.algafood.api.controllers.ProdutoController;
import com.algaworks.algafood.api.controllers.RestauranteProdutoController;
import com.algaworks.algafood.api.controllers.RestauranteProdutoFotoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class ProdutoLinks {

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe ProdutoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto produtoDTO*/
    public Link addSelfLink(ProdutoDTO produtoDTO) {

        return WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador ProdutoController
                .methodOn(ProdutoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(produtoDTO.getId()))    //  método do ProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF);
    }


    /** Cria link para o restaurante desse produto */
    public Link addRestauranteLink(Long restauranteId, String rel){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                .methodOn(RestauranteProdutoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaTodosProdutosDoRestaurante(null, restauranteId)) //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); // Representa o URI indicando que este link aponta para o próprio recurso do restaurante desse produto
    }


    /** Cria link para o restaurante desse produto */
    public Link addProdutoDoRestauranteLink(Long restauranteId, Long produtoId){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                .methodOn(RestauranteProdutoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaProdutoPeloId(restauranteId, produtoId)) //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso
    }


    /** Cria link para a foto desse produto */
    public Link addFotoDoProdutoDoRestauranteLink(Long restauranteId, Long produtoId){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoFotoController
                .methodOn(RestauranteProdutoFotoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .recuperaDadosFoto(restauranteId, produtoId)) //  método do RestauranteProdutoFotoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("foto"); // Representa o URI indicando que este link aponta para a foto desse produto
    }


    /** Cria link da URI mapeada na classe ProdutoController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(ProdutoController.class)
            .withSelfRel();
    }
}
