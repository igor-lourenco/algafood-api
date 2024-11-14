package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.api.controllers.UsuarioGrupoController;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<UsuarioModel, UsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;

    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public UsuarioDTOAssembler() {
        super(UsuarioController.class, UsuarioDTO.class);
    }

    /** Converte classe UsuarioModel para classe UsuarioDTO */
    public UsuarioDTO convertToUsuarioDTO(UsuarioModel usuarioModel) {
        return toModel(usuarioModel);
    }

    @Override
    public UsuarioDTO toModel(UsuarioModel entity) {
        UsuarioDTO usuarioDTO = modelMapper.map(entity, UsuarioDTO.class);

/*      Dessa forma usa methodOn() para referenciar diretamente o método buscaPorId da classe UsuarioController com o ID
      especificado. Ajuda a evitar problemas caso a URL do método mude futuramente */

        usuarioDTO.add(WebMvcLinkBuilder            // adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                .methodOn(UsuarioController.class)  // é usado para referenciar um controlador e um método específico de forma segura.
                .buscaPorId(usuarioDTO.getId()))    //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.SELF));     // Representa o URI indicando que este link aponta para o próprio usuario


        usuarioDTO.add(WebMvcLinkBuilder            //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder.              // cria uma base para o link HATEOAS, apontando para o controlador UsuarioController
                methodOn(UsuarioController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista())                           //  método do UsuarioController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel(IanaLinkRelations.COLLECTION)); // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuário


        usuarioDTO.add(WebMvcLinkBuilder //  adiciona o link HATEOAS ao objeto.
            .linkTo(WebMvcLinkBuilder               // cria uma base para o link HATEOAS, apontando para o controlador UsuarioGrupoController
                .methodOn(UsuarioGrupoController.class)   // é usado para referenciar um controlador e um método específico de forma segura.
                .lista(usuarioDTO.getId())) //  método do UsuarioGrupoController para detectar o mapeamento desse método e cria automaticamente a URL associada.
            .withRel("grupos-usuarios"));    // Representa o URI indicando que este link aponta para o próprio recurso do grupo desse usuario

        return usuarioDTO;
    }


    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends UsuarioModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe UsuarioController para essa coleção
        return super.toCollectionModel(entities)
            .add(WebMvcLinkBuilder
                .linkTo(UsuarioController.class)
                .withSelfRel());
    }
}
