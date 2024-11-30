package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.FormaPagamentoDTO;
import com.algaworks.algafood.api.controllers.FormaPagamentoController;
import com.algaworks.algafood.api.controllers.RestauranteFormaPagamentoController;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.lang.reflect.Method;

@Component
public class FormaPagamentoLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe FormaPagamentoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */


    /** Cria link para o próprio objeto */
    public Link addSelfLink(FormaPagamentoDTO formaPagamentoDTO, ServletWebRequest servletWebRequest){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador FormaPagamentoController
                .methodOn(FormaPagamentoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(formaPagamentoDTO.getId(), servletWebRequest)) // método do FormaPagamentoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para a própria forma de pagamento
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(String rel, ServletWebRequest servletWebRequest){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador FormaPagamentoController
                methodOn(FormaPagamentoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista(servletWebRequest))         //  método do FormaPagamentoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da forma de pagemento
    }


    /** Cria link da URI mapeada na classe FormaPagamentoController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(FormaPagamentoController.class)
            .withSelfRel();
    }


    /** Cria link da URI mapeada na classe RestauranteFormaPagamentoController para esse objeto */
    public Link addSelRestauranteFormaPagamentofCollectionLink(Long resttauranteId){
        return WebMvcLinkBuilder
            .linkTo(WebMvcLinkBuilder
                .methodOn(RestauranteFormaPagamentoController.class)
                .buscaFormaPagamentoPorRestauranteId(resttauranteId))
            .withSelfRel();
    }


    /** Cria link para desassociar o restaurante dessa forma de pagamento*/
    public Link addDesassociaRestauranteDaFormaPagamentoLink(Long restauranteId, Long formaPagamentoId) {
        try {

            Class<?> controllerClass = RestauranteFormaPagamentoController.class;
            Method method = controllerClass.getMethod("desassociaFormaPagamentoDoRestaurante", Long.class, Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, restauranteId, formaPagamentoId)   // é usado para referenciar um controlador e um método específico de forma segura.
                .withRel("desassociar"); // Representa o URI indicando que este link desassocia o restaurante dessa forma de pagamento

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método desassociaFormaPagamentoComRestaurante(Long restauranteId, Long formaPagamentoId) da classe RestauranteFormaPagamentoController");
        }
    }


    /** Cria link para associar o restaurante com forma de pagamento*/
    public Link addAssociaRestauranteDaFormaPagamentoLink(Long restauranteId) {

        String urlRestauranteFormaPagamento =
            WebMvcLinkBuilder.linkTo(RestauranteFormaPagamentoController.class, restauranteId)
                .toUriComponentsBuilder().toUriString();

        String urlComTemplate = urlRestauranteFormaPagamento + "/{formaPagamentoId}";

        return Link.of(
            UriTemplate.of(urlComTemplate),
            "associar"
        );
    }
}
