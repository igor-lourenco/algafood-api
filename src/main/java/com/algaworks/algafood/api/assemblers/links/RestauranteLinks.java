package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.RestauranteDTO;
import com.algaworks.algafood.api.controllers.*;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.hateoas.*;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class RestauranteLinks {

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe RestauranteController com o ID
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
    public Link addCollectionLink(String rel){

       return  WebMvcLinkBuilder          //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.           // cria uma base para o link HATEOAS, apontando para o controlador RestauranteController
                methodOn(RestauranteController.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                        //  método do RestauranteController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel);  // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do restaurante
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


    /** Cria link para os produtos desse restaurante */
    public Link addSelfRestauranteProdutoLink(RestauranteDTO restauranteDTO){

        return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                .methodOn(RestauranteProdutoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaTodosProdutosDoRestaurante(null, restauranteDTO.getId())) //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("produtos"); // Representa o URI indicando que este link aponta para o próprio recurso de produtos desse restaurante
    }


    /** Cria link da URI mapeada na classe RestauranteController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(RestauranteController.class)
            .withSelfRel();
    }


    /** Cria link para alterar o restaurante para: ativo = true*/
    public Link addSelfAtivaRestauranteLink(RestauranteDTO restauranteDTO) {
        try {

            Class<?> controllerClass = RestauranteController.class;
            Method method = controllerClass.getMethod("ativa", Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, restauranteDTO.getId())   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("ativa") // // Adiciona o sub-recurso ao URI atual, no caso seria o '/ativa', mas já está pegando do método LinkTo acima.
                .withRel("ativar"); // Representa o URI indicando que este link altera o restaurante para: ativo = true

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método ativa(Long restauranteId) da classe RestauranteController");
        }
    }


    /** Cria link para alterar o restaurante para: ativo = false*/
    public Link addSelfInativaRestauranteLink(RestauranteDTO restauranteDTO) {
        try {

            Class<?> controllerClass = RestauranteController.class;
            Method method = controllerClass.getMethod("inativa", Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, restauranteDTO.getId())   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("inativa") // // Adiciona o sub-recurso ao URI atual, no caso seria o '/inativa', mas já está pegando do método LinkTo acima.
                .withRel("inativar"); // Representa o URI indicando que este link altera o restaurante para: ativo = false

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método inativa(Long restauranteId) da classe RestauranteController");
        }
    }


    /** Cria link para alterar o restaurante para: aberto = true*/
    public Link addSelfAbreRestauranteLink(RestauranteDTO restauranteDTO) {
        try {

            Class<?> controllerClass = RestauranteController.class;
            Method method = controllerClass.getMethod("abertura", Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, restauranteDTO.getId())   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("abertura") // // Adiciona o sub-recurso ao URI atual, no caso seria o '/abertura', mas já está pegando do método LinkTo acima.
                .withRel("abrir"); // Representa o URI indicando que este link altera o restaurante para: aberto = false

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método abertura(Long restauranteId) da classe RestauranteController");
        }
    }


    /** Cria link para alterar o restaurante para: aberto = false*/
    public Link addSelfFechaRestauranteLink(RestauranteDTO restauranteDTO) {
        try {

            Class<?> controllerClass = RestauranteController.class;
            Method method = controllerClass.getMethod("fechamento", Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, restauranteDTO.getId())   // é usado para referenciar um controlador e um método específico de forma segura.
                //.slash("fechamento") // // Adiciona o sub-recurso ao URI atual, no caso seria o '/fechamento', mas já está pegando do método LinkTo acima.
                .withRel("fechar"); // Representa o URI indicando que este link altera o restaurante para: aberto = false

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método fechamento(Long restauranteId) da classe RestauranteController");
        }
    }


    /** Cria link para a API de restaurantes com o paramêtro da requisição para limitar os campos retornados*/
    public Link addLimitaOsCamposRestauranteLink() {
        Class<?> controllerClass = RestauranteController.class;

        TemplateVariables templates = new TemplateVariables(
            new TemplateVariable("apenasOsCampos", TemplateVariable.VariableType.REQUEST_PARAM));

        String urlPesquisaPedidos = WebMvcLinkBuilder.linkTo(controllerClass).toUri().toString();

        return Link.of(
            UriTemplate.of(urlPesquisaPedidos, templates),
            "limita-campos-restaurantes"
        );
    }


    /** Cria link para a API de restaurantes com o paramêtro da requisição - projecao*/
    public Link addProjecaoRestauranteLink() {
        try {
        Class<?> controllerClass = RestauranteController.class;
        Method method = controllerClass.getMethod("listaComWrapper", String.class);

        TemplateVariables templates = new TemplateVariables(
            new TemplateVariable("projecao", TemplateVariable.VariableType.REQUEST_PARAM));

        String urlPesquisaPedidos = WebMvcLinkBuilder.linkTo(controllerClass, method).toUri().toString();

        return Link.of(
            UriTemplate.of(urlPesquisaPedidos, templates),
            "projecao-campos-restaurantes"
        );

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método listaComWrapper(String projecao) da classe RestauranteController");
        }
    }
}
