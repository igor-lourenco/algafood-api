package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.controllers.GrupoController;
import com.algaworks.algafood.api.controllers.GrupoPermissaoController;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class GrupoLinks {

    /** Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe GrupoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto */
    public Link addSelfLink(GrupoDTO grupoDTO){

        return WebMvcLinkBuilder  // // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder  // cria uma base para o link HATEOAS, apontando para o controlador GrupoController
                .methodOn(GrupoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(grupoDTO.getId())) //  método do GrupoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF); // Representa o URI indicando que este link aponta para o próprio grupo
    }


    /** Cria link para a coleção desse objeto*/
    public Link addCollectionLink(){

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador GrupoController
                methodOn(GrupoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                     //  método do GrupoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do grupo
    }


    /** Cria link da URI mapeada na classe GrupoController para esse objeto */
    public Link addSelfCollectionLink(){
        return WebMvcLinkBuilder
            .linkTo(GrupoController.class)
            .withSelfRel();
    }


    /** Cria link para as permissões desse grupo*/
    public Link addPermissoesDoGrupoLink(GrupoDTO grupoDTO){

        return WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.          // cria uma base para o link HATEOAS, apontando para o controlador GrupoPermissaoController
                methodOn(GrupoPermissaoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaTodasPermissoesDoGrupo(grupoDTO.getId()))    //  método do GrupoPermissaoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("permissoes"); // Representa o URI para a coleção de recursos de permissões desse grupo
    }


    /** Cria link para desassociar o grupo da permissão*/
    public Link addDesassociaGrupoDaPermissaoLink(Long grupoId, Long permissaoId) {
        try {

            Class<?> controllerClass = GrupoPermissaoController.class;
            Method method = controllerClass.getMethod("desassociaPermissao", Long.class, Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method, grupoId, permissaoId)   // é usado para referenciar um controlador e um método específico de forma segura.
                .withRel("desassociar"); // Representa o URI indicando que este link desassocia o grupo da permissão

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método desassociaPermissao(Long grupoId, Long permissaoId) da classe GrupoPermissaoController");
        }
    }


    /** Cria link para associar o grupo da permissão*/
    public Link addAssociaGrupoDaPermissaoLink(Long grupoId) {
        String urlGrupoPermissao =
            WebMvcLinkBuilder.linkTo(GrupoPermissaoController.class, grupoId)
                .toUriComponentsBuilder().toUriString();

        String urlComTemplate = urlGrupoPermissao + "/{permissaoId}";

        return Link.of(
            UriTemplate.of(urlComTemplate),
            "associar"
        );
    }
}
