package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.PermissaoDTO;
import com.algaworks.algafood.api.assemblers.links.PermissaoLinks;
import com.algaworks.algafood.api.controllers.PermissaoController;
import com.algaworks.algafood.domain.models.PermissaoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PermissaoDTOAssembler extends RepresentationModelAssemblerSupport<PermissaoModel, PermissaoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PermissaoLinks permissaoLinks;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public PermissaoDTOAssembler() {
        super(PermissaoController.class, PermissaoDTO.class);
    }

    /** Converte classe PermissaoModel para classe PermissaoDTO */
    public PermissaoDTO convertToGrupoPermissaoDTO(PermissaoModel permissaoModel) {

        PermissaoDTO grupoDTO = modelMapper.map(permissaoModel, PermissaoDTO.class);
        return grupoDTO;
    }


    @Override
    public PermissaoDTO toModel(PermissaoModel permissaoModel) {
        PermissaoDTO permissaoDTO = modelMapper.map(permissaoModel, PermissaoDTO.class);

        // Representa a própria URI mapeada na classe PermissaoController
        permissaoDTO.add(permissaoLinks.addSelfCollectionLink());

        return permissaoDTO;
    }

    @Override
    public CollectionModel<PermissaoDTO> toCollectionModel(Iterable<? extends PermissaoModel> entities) {

    //  Dessa forma adiciona a própria URI mapeada na classe PermissaoController para essa coleção
        return super.toCollectionModel(entities)
            .add(permissaoLinks.addSelfCollectionLink());
    }
}
