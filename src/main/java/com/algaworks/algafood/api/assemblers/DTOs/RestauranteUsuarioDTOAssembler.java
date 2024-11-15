package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.RestauranteUsuarioDTO;
import com.algaworks.algafood.api.controllers.RestauranteUsuarioController;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestauranteUsuarioDTOAssembler extends RepresentationModelAssemblerSupport<UsuarioModel, RestauranteUsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;

    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public RestauranteUsuarioDTOAssembler() {
        super(RestauranteUsuarioController.class, RestauranteUsuarioDTO.class);
    }


    /** Converte classe UsuarioModel para classe  RestauranteUsuarioDTO */
    public RestauranteUsuarioDTO convertToRestauranteUsuarioDTO(UsuarioModel usuarioModel) {
        return toModel(usuarioModel);
    }

    @Override
    public RestauranteUsuarioDTO toModel(UsuarioModel entity) {
        RestauranteUsuarioDTO entityDTO = modelMapper.map(entity, RestauranteUsuarioDTO.class);

/*      Dessa forma usa methodOn() para referenciar diretamente os métodos com a URI mapeada da classe UsuarioController com o ID
        especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        entityDTO.add(WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                .methodOn(UsuarioController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(entityDTO.getId()))    //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF));     // Representa o URI indicando que este link aponta para o próprio usuario


        entityDTO.add(WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                 methodOn(UsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION)); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuário

        return entityDTO;
    }


    @Override
    public CollectionModel<RestauranteUsuarioDTO> toCollectionModel(Iterable<? extends UsuarioModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe UsuarioController para essa coleção
        return super.toCollectionModel(entities)
            .add(WebMvcLinkBuilder
                .linkTo(UsuarioController.class)
                .withSelfRel());
    }
}
