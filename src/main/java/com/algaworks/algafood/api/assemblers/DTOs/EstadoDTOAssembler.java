package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.EstadoDTO;
import com.algaworks.algafood.api.assemblers.links.EstadoLinks;
import com.algaworks.algafood.api.controllers.EstadoController;
import com.algaworks.algafood.domain.models.EstadoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EstadoDTOAssembler extends RepresentationModelAssemblerSupport<EstadoModel, EstadoDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EstadoLinks estadoLinks;


    //  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public EstadoDTOAssembler() {
        super(EstadoController.class, EstadoDTO.class);
    }


    /** Converte classe EstadoModel para classe EstadoDTO */
    public EstadoDTO convertToEstadoDTOBuilder(EstadoModel estadoModel) {
        return toModel(estadoModel);
    }


    @Override
    public EstadoDTO toModel(EstadoModel entity) {
        EstadoDTO estadoDTO = modelMapper.map(entity, EstadoDTO.class);

        // Representa o URI indicando que este link aponta para o próprio estado
        estadoDTO.add(estadoLinks.addSelfLink(estadoDTO));

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual do estado
        estadoDTO.add(estadoLinks.addCollectionLink("collection"));

        return estadoDTO;
    }


    @Override
    public CollectionModel<EstadoDTO> toCollectionModel(Iterable<? extends EstadoModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe EstadoController para essa coleção
        return super.toCollectionModel(entities)
            .add(estadoLinks.addSelfCollectionLink());
    }
}
