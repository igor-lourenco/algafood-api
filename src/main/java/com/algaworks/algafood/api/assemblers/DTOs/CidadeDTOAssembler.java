package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTO;
import com.algaworks.algafood.api.assemblers.links.CidadeLinks;
import com.algaworks.algafood.api.controllers.CidadeController;
import com.algaworks.algafood.domain.models.CidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTOAssembler extends RepresentationModelAssemblerSupport<CidadeModel, CidadeDTO> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CidadeLinks cidadeLinks;

//  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public CidadeDTOAssembler() {
        super(CidadeController.class, CidadeDTO.class);
    }


    /** Converte classe CidadeModel para classe CidadeDTO */
    public CidadeDTO convertToCidadeDTO(CidadeModel cidadeModel) {
        return toModel(cidadeModel);
    }

    @Override
    public CidadeDTO toModel(CidadeModel entity) {
        CidadeDTO cidadeDTO = modelMapper.map(entity, CidadeDTO.class);

        // Representa o URI indicando que este link aponta para a própria cidade
        cidadeDTO.add(cidadeLinks.addSelfLink(cidadeDTO));


        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade
        cidadeDTO.add(cidadeLinks.addCollectionLink());


        // Representa o URI indicando que este link aponta para o próprio recurso do estado dessa cidade
        cidadeDTO.getEstado().add(cidadeLinks.addEstadoLink(cidadeDTO));

        return cidadeDTO;
    }



    @Override
    public CollectionModel<CidadeDTO> toCollectionModel(Iterable<? extends CidadeModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe CidadeController para essa coleção
        return super.toCollectionModel(entities)
            .add(cidadeLinks.addSelfCollection());
    }
}
