package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.controllers.*;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component
public class PedidoLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe UsuarioController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */


    /** Cria link para o próprio objeto */
    public Link addSelfLink(String codigoPedido){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador PedidoController
                .methodOn(PedidoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPeloCodigo(codigoPedido)) // método do PedidoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio pedido
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador PedidoController
                methodOn(PedidoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())         //  método do PedidoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do pedido
    }



    /** Cria link para a forma de pagamento no objeto 'formaPagamento' do pedido */
    public Link addSelfFormaPagamentoLink(Long formaPagamentoId, ServletWebRequest servletWebRequest){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador FormaPagamentoController
                .methodOn(FormaPagamentoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(formaPagamentoId, servletWebRequest)) //  método do FormaPagamentoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso de forma de pagamento desse pedido
    }


    /** Cria link para o restaurante no objeto 'restaurante' do pedido */
    public Link addSelfRestauranteLink(Long restauranteId){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador RestauranteController
                .methodOn(RestauranteController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(restauranteId)) //  método do RestauranteController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso de restaurante desse pedido
    }


    /** Cria link para o usuário no objeto 'cliente' do pedido */
    public Link addSelfClienteLink(Long clienteId){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                .methodOn(UsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(clienteId)) //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso de cliente desse pedido
    }


    /** Cria link para a cidade no objeto 'enderecoEntrega' do pedido */
    public Link addSelfCidadeLink(Long cidadeId){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador CidadeController
                .methodOn(CidadeController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(cidadeId)) //  método do CidadeController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso de cidade desse pedido
    }


    /** Cria link para o produto do restaurante no objeto 'itens' de itemPedidoDTO do pedido */
    public Link addSelfProdutoDoItemPedidoLink(Long restauranteId, Long produtoId){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                .methodOn(RestauranteProdutoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaProdutoPeloId(restauranteId, produtoId)) //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio recurso do produto do item de pedido desse pedido
    }

}
