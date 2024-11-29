package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.GrupoDTO;
import com.algaworks.algafood.api.assemblers.links.GrupoLinks;
import com.algaworks.algafood.api.controllers.GrupoController;
import com.algaworks.algafood.domain.models.GrupoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class GrupoDTOAssembler extends RepresentationModelAssemblerSupport<GrupoModel, GrupoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GrupoLinks grupoLinks;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public GrupoDTOAssembler() {
        super(GrupoController.class, GrupoDTO.class);
    }


    /** Converte classe GrupoModel para classe GrupoDTO.GrupoDTOBuilder */
    public GrupoDTO convertToGrupoDTO(GrupoModel grupoModel) {
        return toModel(grupoModel);
    }


    @Override
    public GrupoDTO toModel(GrupoModel grupoModel) {
        GrupoDTO grupoDTO = modelMapper.map(grupoModel, GrupoDTO.class);

        // Representa o URI indicando que este link aponta para o próprio grupo
        grupoDTO.add(grupoLinks.addSelfLink(grupoDTO));

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do grupo
        grupoDTO.add(grupoLinks.addCollectionLink());

        // Representa o URI para a coleção de recursos de permissões desse grupo
        grupoDTO.add(grupoLinks.addPermissoesDoGrupoLink(grupoDTO));

        return grupoDTO;

    }


    @Override
    public CollectionModel<GrupoDTO> toCollectionModel(Iterable<? extends GrupoModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe GrupoController para essa coleção
        return super.toCollectionModel(entities)
            .add(grupoLinks.addSelfCollectionLink());
    }
}
