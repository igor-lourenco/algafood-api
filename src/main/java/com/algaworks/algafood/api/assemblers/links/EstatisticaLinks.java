package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.controllers.EstatisticasController;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.filters.VendaDiariaFilter;
import lombok.extern.log4j.Log4j2;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.TemplateVariable;
import org.springframework.hateoas.TemplateVariables;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Component
public class EstatisticaLinks {

    /* Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe EstatisticasController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o recurso de consulta vendas diarias */
    public Link addVendaDiariaLink(String rel){
        try {
            Class<?> controllerClass = EstatisticasController.class;
            Class<?> vendaDiariaFilterClass = VendaDiariaFilter.class;
            Method method = controllerClass.getMethod("consultaVendasDiarias", vendaDiariaFilterClass, String.class); // pega o método da classe

            Field[] atributosDaClasse = vendaDiariaFilterClass.getDeclaredFields(); // pega os atributos da classe
            List<Field> listaAtributos = Arrays.asList(atributosDaClasse); // converte para lista

            List<TemplateVariable> templateLista = new ArrayList<>();

            listaAtributos.forEach( atributo -> templateLista.add(
                new TemplateVariable(atributo.getName(), TemplateVariable.VariableType.REQUEST_PARAM))); // adiciona na lista de TemplateVariable

            templateLista.add(new TemplateVariable("timeOffset", TemplateVariable.VariableType.REQUEST_PARAM));

            TemplateVariables templates = new TemplateVariables(templateLista); // instancia um TemplateVariables com a lista de TemplateVariable

            String urlEstatisticas = WebMvcLinkBuilder.linkTo(controllerClass, method, new VendaDiariaFilter()).toUri().toString();

            return Link.of(UriTemplate.of(urlEstatisticas, templates), rel);

        } catch (NoSuchMethodException e) {
            log.error("EEROR :: {}", e.getMessage());
            throw new EntidadeNaoEncontradaException(
                "Não foi possível encontra o método consultaVendasDiarias(VendaDiariaFilter pedidoFilter, String timeOffset) da classe EstatisticasController");
        }
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(String rel){

        return WebMvcLinkBuilder       //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.    // cria uma base para o link HATEOAS, apontando para o controlador EstatisticasController
                methodOn(EstatisticasController.class) // é usado para referenciar um controlador e um método específico de forma segura.
                .estatisticas()) //  método do EstatisticasController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(rel); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual
    }
}
