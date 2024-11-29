package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.UsuarioGrupoDTO;
import com.algaworks.algafood.api.assemblers.links.UsuarioGrupoLinks;
import com.algaworks.algafood.api.controllers.UsuarioGrupoController;
import com.algaworks.algafood.domain.models.GrupoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class UsuarioGrupoDTOAssembler extends RepresentationModelAssemblerSupport<GrupoModel, UsuarioGrupoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UsuarioGrupoLinks links;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public UsuarioGrupoDTOAssembler() {
        super(UsuarioGrupoController.class, UsuarioGrupoDTO.class);
    }


    /** Converte classe GrupoModel para classe UsuarioGrupoDTO */
    public UsuarioGrupoDTO convertToUsuarioDTO(GrupoModel grupoModel) {

        UsuarioGrupoDTO usuarioDTO = toModel(grupoModel);

        return usuarioDTO;
    }

    @Override
    public UsuarioGrupoDTO toModel(GrupoModel grupoModel) {
        UsuarioGrupoDTO usuarioGrupoDTO = modelMapper.map(grupoModel, UsuarioGrupoDTO.class);

        // Representa o URI indicando que este link aponta para o grupo desse usuario
        usuarioGrupoDTO.add(links.addSelfLink(usuarioGrupoDTO));

        // Representa o URI indicando que este link aponta para a coleção de grupos
        usuarioGrupoDTO.add(links.addGrupoLink());

        // Representa o URI indicando que este link aponta as permissões desse grupo
        usuarioGrupoDTO.add(links.addPermissoesGrupoLink(usuarioGrupoDTO));

        return usuarioGrupoDTO;

    }


    public CollectionModel<UsuarioGrupoDTO> toCollectionUsuarioGrupoModel(Long usuarioId, Iterable<? extends GrupoModel> entities) {
        CollectionModel<UsuarioGrupoDTO> usuarioGrupoDTOs = super.toCollectionModel(entities);

        // Representa o URI para desassociar o usuario desse grupo
        usuarioGrupoDTOs.forEach(usuarioGrupoDTO ->
            usuarioGrupoDTO.add(links.addDesassociaGrupoDoUsuarioLink(usuarioGrupoDTO.getId(), usuarioId)));

        // Representa o URI para aassociar o usuario com esse grupo
        usuarioGrupoDTOs.add(links.addAssociaGrupoDoUsuarioLink(usuarioId));

        return usuarioGrupoDTOs;
    }
}
