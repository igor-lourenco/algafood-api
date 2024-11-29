package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.GrupoPermissaoDTO;
import com.algaworks.algafood.api.assemblers.links.GrupoLinks;
import com.algaworks.algafood.api.controllers.GrupoPermissaoController;
import com.algaworks.algafood.domain.models.PermissaoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoPermissaoDTOAssembler extends RepresentationModelAssemblerSupport<PermissaoModel, GrupoPermissaoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GrupoLinks grupoLinks;

    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public GrupoPermissaoDTOAssembler() {
        super(GrupoPermissaoController.class, GrupoPermissaoDTO.class);
    }


    /** Converte classe PermissaoModel para classe GrupoPermissaoDTO com o id do grupo*/
    public GrupoPermissaoDTO convertToGrupoPermissaoDTO(Long grupoId, PermissaoModel permissaoModel) {
        GrupoPermissaoDTO grupoPermissaoDTO = toModel(permissaoModel);

        grupoPermissaoDTO.add(grupoLinks.addDesassociaGrupoDaPermissaoLink(grupoId, permissaoModel.getId()));

        return grupoPermissaoDTO;
    }


    @Override
    public GrupoPermissaoDTO toModel(PermissaoModel permissaoModel) {
        GrupoPermissaoDTO grupoPermissaoDTO = modelMapper.map(permissaoModel, GrupoPermissaoDTO.class);
        return grupoPermissaoDTO;
    }


    public CollectionModel<GrupoPermissaoDTO> toCollectionGrupoPermissaoModel(Long grupoId, Iterable<? extends PermissaoModel> entities) {
        CollectionModel<GrupoPermissaoDTO> grupoPermissaoDTOs = super.toCollectionModel(entities);

        grupoPermissaoDTOs.forEach(dto ->
            dto.add(grupoLinks.addDesassociaGrupoDaPermissaoLink(grupoId, dto.getId())));

        grupoPermissaoDTOs.add(grupoLinks.addAssociaGrupoDaPermissaoLink(grupoId));

        return grupoPermissaoDTOs;
    }
}
