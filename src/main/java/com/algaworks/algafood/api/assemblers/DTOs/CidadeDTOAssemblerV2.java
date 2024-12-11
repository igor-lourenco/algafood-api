package com.algaworks.algafood.api.assemblers.DTOs;

import com.algaworks.algafood.api.DTOs.CidadeDTOV2;
import com.algaworks.algafood.api.assemblers.links.CidadeLinksV2;
import com.algaworks.algafood.api.controllers.CidadeController;
import com.algaworks.algafood.domain.models.CidadeModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeDTOAssemblerV2 extends RepresentationModelAssemblerSupport<CidadeModel, CidadeDTOV2> {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CidadeLinksV2 cidadeLinksV2;


//  Construtor obrigatório para criar um novo RepresentationModelAssemblerSupport usando a classe de controlador e o tipo de recurso fornecidos como base.
    public CidadeDTOAssemblerV2() {
        super(CidadeController.class, CidadeDTOV2.class);
    }


    /** Converte classe CidadeModel para classe CidadeDTO */
    public CidadeDTOV2 convertToCidadeDTOV2(CidadeModel cidadeModel) {
        return toModel(cidadeModel);
    }


    @Override
    public CidadeDTOV2 toModel(CidadeModel entity) {
        CidadeDTOV2 cidadeDTOV2 = modelMapper.map(entity, CidadeDTOV2.class);

        // Representa o URI indicando que este link aponta para a própria cidade
        cidadeDTOV2.add(cidadeLinksV2.addSelfLink(cidadeDTOV2));

        // Representa o URI para a coleção de recursos do mesmo tipo do recurso atual da cidade
        cidadeDTOV2.add(cidadeLinksV2.addCollectionLink("collection"));


        return cidadeDTOV2;
    }


    @Override
    public CollectionModel<CidadeDTOV2> toCollectionModel(Iterable<? extends CidadeModel> entities) {

//      Dessa forma adiciona a própria URI mapeada na classe CidadeController para essa coleção
        return super.toCollectionModel(entities)
            .add(cidadeLinksV2.addSelfCollectionLink());
    }
}
