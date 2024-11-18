package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.UsuarioDTO;
import com.algaworks.algafood.api.assemblers.links.UsuarioLinks;
import com.algaworks.algafood.api.controllers.UsuarioController;
import com.algaworks.algafood.domain.models.UsuarioModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UsuarioDTOAssembler extends RepresentationModelAssemblerSupport<UsuarioModel, UsuarioDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioLinks usuarioLinks;


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

        // Representa o URI indicando que este link aponta para o próprio usuario
        usuarioDTO.add(usuarioLinks.addSelfLink(usuarioDTO));

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do usuário
        usuarioDTO.add(usuarioLinks.addCollectionLink());

        // Representa o URI indicando que este link aponta para o próprio recurso do grupo desse usuario
        usuarioDTO.add(usuarioLinks.addSelfGruposUsuarioLink(usuarioDTO, "grupos-usuario"));

        return usuarioDTO;
    }


    @Override
    public CollectionModel<UsuarioDTO> toCollectionModel(Iterable<? extends UsuarioModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe UsuarioController para essa coleção
        return super.toCollectionModel(entities)
            .add(usuarioLinks.addSelfCollectionLink());
    }
}
