package com.algaworks.algafood.api.assemblers.links;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.api.controllers.GrupoController;
import com.algaworks.algafood.api.controllers.GrupoPermissaoController;
import com.algaworks.algafood.api.controllers.UsuarioGrupoController;
import com.algaworks.algafood.domain.exceptions.EntidadeNaoEncontradaException;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class UsuarioGrupoLinks {

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe UsuarioGrupoController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

    /** Cria link para o próprio objeto usuarioGrupoDTO*/
    public Link addSelfLink(UsuarioGrupoDTO usuarioGrupoDTO) {

        return WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder              // cria uma base para o link HATEOAS, apontando para o controlador GrupoController
                .methodOn(GrupoController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(usuarioGrupoDTO.getId()))    //  método do GrupoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF);
    }


    /** Cria link para a coleção de grupos */
    public Link addGrupoLink(){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador GrupoController
                .methodOn(GrupoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista()) //  método do GrupoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("grupos"); // Representa o URI indicando que este link aponta para a coleção de grupos
    }


    /** Cria link para as permissões do grupo que esse usuario está associado */
    public Link addPermissoesGrupoLink(UsuarioGrupoDTO usuarioGrupoDTO){

       return  WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder      // cria uma base para o link HATEOAS, apontando para o controlador RestauranteProdutoController
                .methodOn(GrupoPermissaoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaTodasPermissoesDoGrupo(usuarioGrupoDTO.getId())) //  método do RestauranteProdutoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("permissoes"); // Representa o URI indicando que este link aponta as permissões desse grupo associado a esse usuario
    }


    /** Cria link para desassociar o usuario desse grupo */
    public Link addDesassociaGrupoDoUsuarioLink(Long grupoId, Long usuarioId){
        try {

            Class<?> controllerClass = UsuarioGrupoController.class;
            Method method = controllerClass.getMethod("desassocia", Long.class, Long.class); // pega o método da classe

            return WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
                .linkTo(controllerClass, method,  usuarioId, grupoId)   // é usado para referenciar um controlador e um método específico de forma segura.
                .withRel("desassociar"); // Representa o URI indicando que este link desassocia o usuario desse grupo

        } catch (NoSuchMethodException e) {
            System.out.println("EEROR :: " + e.getMessage());
            throw new EntidadeNaoEncontradaException("Não foi possível encontra o método desassocia(Long grupoId, Long usuarioId) da classe UsuarioGrupoController");
        }
    }


    /** Cria link para associar o usuario com o grupo*/
    public Link addAssociaGrupoDoUsuarioLink(Long usuarioId) {

        String urlUsuarioGrupo =
            WebMvcLinkBuilder.linkTo(UsuarioGrupoController.class, usuarioId)
                .toUriComponentsBuilder().toUriString();

        String urlComTemplate = urlUsuarioGrupo + "/{grupoId}";

        return Link.of(
            UriTemplate.of(urlComTemplate),
            "associar"
        );
    }
}
