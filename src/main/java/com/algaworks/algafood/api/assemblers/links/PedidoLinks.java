package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.controllers.*;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.filters.PedidoFilter;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class PedidoLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe PedidoController com o ID
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


    /** Cria link para alterar o status desse pedido para 'CONFIRMADO'*/
    public Link addSelfConfirmaPedidoLink(String codigoPedido) {
        try {

            Class<?> controllerClass = PedidoStatusController.class;
            Method method = controllerClass.getMethod("confirmaPedido", String.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, codigoPedido)   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("confirma") // // Adiciona o sub-recurso ao URI atual, no caso seria o '/confirma', mas já está pegando do método LinkTo acima.
                .withRel("confirma-pedido"); // Representa o URI indicando que este link altera o status do pedido para 'CONFIRMADO'

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método confirmaPedido(String codigoPedido) da classe PedidoStatusController");
        }
    }


    /** Cria link para alterar o status desse pedido para 'ENTREGUE'*/
    public Link addSelfEntregaPedidoLink(String codigoPedido) {
        try {

            Class<?> controllerClass = PedidoStatusController.class;
            Method method = controllerClass.getMethod("entregaPedido", String.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, codigoPedido)   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("entregue") // Adiciona o sub-recurso ao URI atual, no caso seria o '/'entregue', mas já está pegando do método LinkTo acima.
                .withRel("entrega-pedido"); // Representa o URI indicando que este link altera o status do pedido para 'ENTREGUE'

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método entregaPedido(String codigoPedido) da classe PedidoStatusController");
        }
    }


    /** Cria link para alterar o status desse pedido para 'CANCELADO'*/
    public Link addSelfCancelaPedidoLink(String codigoPedido) {
        try {

            Class<?> controllerClass = PedidoStatusController.class;
            Method method = controllerClass.getMethod("cancelaPedido", String.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, codigoPedido)   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("cancela") // // Adiciona o sub-recurso ao URI atual, no caso seria o '/cancela', mas já está pegando do método LinkTo acima.
                .withRel("cancela-pedido"); // Representa o URI indicando que este link altera o status do pedido para 'CANCELADO'

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método cancelaPedido(String codigoPedido) da classe PedidoStatusController");
        }
    }

    /** Cria link para a API de pesquisa pedido com os paramêtros da requisição */
    public Link addSelfPesquisaPedidoLink() {
        try {
            Class<?> controllerClass = PedidoController.class;
            Class<?> pedidoFilterClass = PedidoFilter.class;

            Method method = controllerClass.getMethod("pesquisa", pedidoFilterClass); // pega o método da classe

//            Exemplo simples de instanciar um TemplateVariables
//            TemplateVariables templates = new TemplateVariables(
//                new TemplateVariable("clientId", TemplateVariable.VariableType.REQUEST_PARAM),
//                new TemplateVariable("restauranteId", TemplateVariable.VariableType.REQUEST_PARAM),
//                new TemplateVariable("dataCriacaoInicio", TemplateVariable.VariableType.REQUEST_PARAM),
//                new TemplateVariable("dataCriacaoFim", TemplateVariable.VariableType.REQUEST_PARAM_CONTINUED, "teste")
//            );

            List<TemplateVariable> templateLista = new ArrayList<>();

            Field[] atributosDaClasse = pedidoFilterClass.getDeclaredFields(); // pega os atributos da classe
            List<Field> listaAtributos = Arrays.asList(atributosDaClasse); // converte para lista

            listaAtributos.forEach( atributo -> templateLista.add(
                new TemplateVariable(atributo.getName(), TemplateVariable.VariableType.REQUEST_PARAM))); // adiciona na lista de TemplateVariable

            TemplateVariables templates = new TemplateVariables(templateLista); // instancia um TemplateVariables com a lista de TemplateVariable

            String urlPesquisaPedidos = WebMvcLinkBuilder.linkTo(controllerClass, method, new PedidoFilter()).toUri().toString();

            return Link.of(
                UriTemplate.of(urlPesquisaPedidos, templates),
                "pesquisa-pedidos"
            );

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método pesquisa(PedidoFilter pedidoFilter) da classe PedidoController");
        }
    }

    /** Cria link para a API de pesquisa paginada do pedido com os paramêtros da requisição */
    public Link addSelfPesquisaPaginadaPedidoLink() {
        try {
            Class<?> controllerClass = PedidoController.class;
            Class<?> pedidoFilterClass = PedidoFilter.class;

            Method method = controllerClass.getMethod("pesquisaPage", PedidoFilter.class, Pageable.class); // pega o método da classe

            List<TemplateVariable> templateLista = new ArrayList<>();

            // paramêtros da requisição da paginação
            templateLista.add(new TemplateVariable("page", TemplateVariable.VariableType.REQUEST_PARAM));
            templateLista.add(new TemplateVariable("sort", TemplateVariable.VariableType.REQUEST_PARAM));
            templateLista.add(new TemplateVariable("size", TemplateVariable.VariableType.REQUEST_PARAM));

            Field[] atributosDaClasse = pedidoFilterClass.getDeclaredFields(); // pega os atributos da classe
            List<Field> listaAtributos = Arrays.asList(atributosDaClasse); // converte para lista

            listaAtributos.forEach( atributo -> templateLista.add(
                new TemplateVariable(atributo.getName(), TemplateVariable.VariableType.REQUEST_PARAM))); // adiciona na lista de TemplateVariable

            TemplateVariables templates = new TemplateVariables(templateLista); // instancia um TemplateVariables com a lista de TemplateVariable

            String urlPesquisaPedidos = WebMvcLinkBuilder.linkTo(controllerClass, method, new PedidoFilter()).toUri().toString();

            return Link.of(
                UriTemplate.of(urlPesquisaPedidos, templates),
                "pesquisa-paginada-pedidos"
            );

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método pesquisa(PedidoFilter pedidoFilter) da classe PedidoController");
        }
    }

}
