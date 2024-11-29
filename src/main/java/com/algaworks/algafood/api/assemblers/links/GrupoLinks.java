package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.controllers.GrupoController;
import com.algaworks.algafood.api.controllers.GrupoPermissaoController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

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
}
